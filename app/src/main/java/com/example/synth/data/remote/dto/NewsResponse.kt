package com.example.synth.data.remote.dto

import com.example.synth.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)