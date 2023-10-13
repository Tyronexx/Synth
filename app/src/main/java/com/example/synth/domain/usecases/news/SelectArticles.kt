package com.example.synth.domain.usecases.news

import com.example.synth.domain.model.Article
import com.example.synth.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SelectArticles(
    private val newsRepository: NewsRepository
) {

    operator fun invoke(): Flow<List<Article>> {
        return newsRepository.selectArticles()
    }
}