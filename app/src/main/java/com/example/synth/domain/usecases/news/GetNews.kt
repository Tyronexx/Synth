package com.example.synth.domain.usecases.news

import androidx.paging.PagingData
import com.example.synth.domain.model.Article
import com.example.synth.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetNews (
    private val newsRepository: NewsRepository
){

    //    list of Articles (i.e query) cause were using Paging
    operator fun invoke(sources: List<String>): Flow<PagingData<Article>> {
        return newsRepository.getNews(sources = sources)
    }
}