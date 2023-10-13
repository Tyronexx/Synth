package com.example.synth.domain.repository

import androidx.paging.PagingData
import com.example.synth.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    //    API
//    list (of sources) cause we're using paging  (so we're getting a list of pages)
    fun getNews(sources: List<String>): Flow<PagingData<Article>>

    fun searchNews(searchQuery: String ,sources: List<String>): Flow<PagingData<Article>>

    //    DB
    suspend fun upsertArticle(article: Article)

    suspend fun deleteArticle(article: Article)

    fun selectArticles(): Flow<List<Article>>

    suspend fun selectArticle(url: String): Article?

}