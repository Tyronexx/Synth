package com.example.synth.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.synth.domain.model.Article

class SearchNewsPagingSource(
    private val newsApi: NewsApi,
    private val searchQuery: String,
    private val sources: String
): PagingSource<Int, Article>(){

    private var totalNewsCount = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        //        get page from params
        val page = params.key ?: 1  //if key is null start from page 1

        return try {
//            make request to server (get response)
            val newsResponse = newsApi.searchNews(searchQuery = searchQuery, page = page, sources = sources)

            totalNewsCount += newsResponse.articles.size    //number of articles we got in that single request. Increase "totalNewsCount" based on each article we get
//          Get all articles  (distinctBy removes duplicate items from list)
            val articles = newsResponse.articles.distinctBy { it.title }
            LoadResult.Page(
                data = articles,
//                if nextKey is null we'll stop loading new results else
                nextKey = if (totalNewsCount == newsResponse.totalResults) null else page + 1,  //if true stop paging(returning new results) else add 1 to page
                prevKey = null
            )

        }catch (e: Exception){
            e.printStackTrace()
            LoadResult.Error(
                throwable = e
            )
        }
    }


    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}