package com.example.synth.domain.usecases.news

import com.example.synth.domain.model.Article
import com.example.synth.domain.repository.NewsRepository

class DeleteArticle (
    private val newsRepository: NewsRepository
){


    suspend operator fun invoke(article: Article){
        newsRepository.deleteArticle(article = article)
    }
}