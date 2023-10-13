package com.example.synth.presentation.search

import androidx.paging.PagingData
import com.example.synth.domain.model.Article
import kotlinx.coroutines.flow.Flow

data class SearchState (

    val searchQuery: String = "",
//    result from paging 3
    val articles: Flow<PagingData<Article>>? = null
){

}