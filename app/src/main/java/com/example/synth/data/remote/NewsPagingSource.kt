package com.example.synth.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.synth.domain.model.Article

class NewsPagingSource(
    private val newsApi: NewsApi,
    private val sources: String
) : PagingSource<Int, Article>(){

//    make request and return that article
//    params can be used to get the page

    //    way to determine when to stop the paging (depends on API)
    private var totalNewsCount = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
//        get page from params
        val page = params.key ?: 1  //if key is null start from page 1

        return try {
//            make request to server (get response)
            val newsResponse = newsApi.getNews(page = page, sources = sources)

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

    //    used to return page when we call refresh function or when we load the news for the first time
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
//00:12:16
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }

    }

}