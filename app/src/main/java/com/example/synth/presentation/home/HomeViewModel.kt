package com.example.synth.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.synth.domain.usecases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

//We don't use state class for home page cause home page has only one state (paging data)


//inject newsUseCases instead of newsRepository
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
): ViewModel() {


    val news = newsUseCases.getNews(
//      news sources from documentation of api
//        specify the sources we want to get news from
        sources = listOf("bbc-news", "abc-news", "al-jazeera-english", "google-news", "le-monde")
    ).cachedIn(viewModelScope) //save from config changes
}