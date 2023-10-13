package com.example.synth.domain.usecases.news

import com.example.synth.domain.model.Article
import com.example.synth.domain.repository.NewsRepository

class UpsertArticle(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(article: Article){
        newsRepository.upsertArticle(article = article)
    }

}