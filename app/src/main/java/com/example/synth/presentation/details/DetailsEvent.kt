package com.example.synth.presentation.details

import com.example.synth.domain.model.Article

sealed class DetailsEvent {

    //    save/update/delete article
    data class UpsertDeleteArticle(val article: Article) : DetailsEvent()

    object RemoveSideEffect : DetailsEvent()
}