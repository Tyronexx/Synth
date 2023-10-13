package com.example.synth.domain.usecases.news

import com.example.synth.presentation.search.SearchEvent


//wrapper for all news use cases
data class NewsUseCases(
    val getNews: GetNews,
    val searchNews: SearchNews,
    val upsertArticle: UpsertArticle,
    val deleteArticle: DeleteArticle,
    val selectArticle: SelectArticle,
    val selectArticles: SelectArticles


//    val getArticles: GetArticles,
//    val getArticle: GetArticle
)
