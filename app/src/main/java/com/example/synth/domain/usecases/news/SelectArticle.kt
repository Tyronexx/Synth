package com.example.synth.domain.usecases.news

import com.example.synth.domain.model.Article
import com.example.synth.domain.repository.NewsRepository

class SelectArticle (
    private val newsRepository: NewsRepository
){

    suspend operator fun invoke(url: String): Article?{
        return newsRepository.selectArticle(url)
    }
}