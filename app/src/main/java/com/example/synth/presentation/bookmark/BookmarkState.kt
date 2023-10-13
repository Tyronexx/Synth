package com.example.synth.presentation.bookmark

import com.example.synth.domain.model.Article

data class BookmarkState(
    val articles: List<Article> = emptyList()
)
