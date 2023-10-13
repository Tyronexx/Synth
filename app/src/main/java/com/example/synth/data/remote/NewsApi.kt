package com.example.synth.data.remote

import com.example.synth.data.remote.dto.NewsResponse
import com.example.synth.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    //    news articles
    @GET("everything")
    suspend fun getNews(
        @Query("page") page: Int,
        @Query("sources") sources: String,
        @Query("apiKey") apikey: String = API_KEY
    ) : NewsResponse

    //    search (same endpoint)
    @GET("everything")
    suspend fun searchNews(
        @Query("q") searchQuery: String,
        @Query("page") page: Int,
        @Query("sources") sources: String,
        @Query("apiKey") apikey: String = API_KEY
    ) : NewsResponse
}
