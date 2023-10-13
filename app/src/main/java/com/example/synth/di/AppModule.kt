package com.example.synth.di

import android.app.Application
import androidx.room.Room
import com.example.synth.data.local.NewsDao
import com.example.synth.data.local.NewsDatabase
import com.example.synth.data.local.NewsTypeConverter
import com.example.synth.data.manager.LocalUserManagerImpl
import com.example.synth.data.remote.NewsApi
import com.example.synth.data.repository.NewsRepositoryImpl
import com.example.synth.domain.manager.LocalUserManager
import com.example.synth.domain.repository.NewsRepository
import com.example.synth.domain.usecases.app_entry.AppEntryUseCases
import com.example.synth.domain.usecases.app_entry.ReadAppEntry
import com.example.synth.domain.usecases.app_entry.SaveAppEntry
import com.example.synth.domain.usecases.news.DeleteArticle
import com.example.synth.domain.usecases.news.GetNews
import com.example.synth.domain.usecases.news.NewsUseCases
import com.example.synth.domain.usecases.news.SearchNews
import com.example.synth.domain.usecases.news.SelectArticle
import com.example.synth.domain.usecases.news.SelectArticles
import com.example.synth.domain.usecases.news.UpsertArticle
import com.example.synth.util.Constants.BASE_URL
import com.example.synth.util.Constants.NEWS_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    //    provide local user manager implementation
    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManager = LocalUserManagerImpl(application)

    //    provide app entry use cases
    @Provides
    @Singleton
    fun providesAppEntryUseCases(
        localUserManager: LocalUserManager
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )




    //    provide newsApi (needed for 'provideNewsRepository' below)
    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }


    //    provide news repository (needed in 'provideNewsUseCases' below)
    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi,
        newsDao: NewsDao
    ): NewsRepository = NewsRepositoryImpl(
        newsApi,
        newsDao
    )

    //    provide news use cases
    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository,
        newsDao: NewsDao
    ): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository),
            upsertArticle = UpsertArticle(newsRepository),
            deleteArticle = DeleteArticle(newsRepository),
// //           getArticles = GetArticles(newsDao),
            selectArticle = SelectArticle(newsRepository),
            selectArticles = SelectArticles(newsRepository)
//     //       getArticle = GetArticle(newsDao),
        )
    }


    //    provide database
    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ): NewsDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = NEWS_DATABASE_NAME
        ).addTypeConverter(NewsTypeConverter())
            .fallbackToDestructiveMigration()
            .build()
    }

    //    provide DAO object
    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ): NewsDao = newsDatabase.newsDao



}