package com.survivalcoding.network_apps.feature_paging.data.datasource.network

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.survivalcoding.network_apps.feature_paging.data.datasource.network.service.JsonService
import com.survivalcoding.network_apps.feature_paging.domain.model.Post
import kotlinx.coroutines.delay
import java.lang.Exception

class PostPagingDataSource(
    private val service: JsonService,
) : PagingSource<Int, Post>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Post> {
        //return LoadResult.Error(Exception("error"))
        val nextPageNumber = params.key ?: 1
        return try {
            val response = service.getPosts(nextPageNumber, 20)
            //초기에는 40개 중간은 20개를 호출하는 것으로 보인다.
            LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = nextPageNumber + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, Post>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}