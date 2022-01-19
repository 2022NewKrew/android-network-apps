package com.survivalcoding.network_apps.feature_paging.data.datasource.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.survivalcoding.network_apps.feature_paging.data.datasource.remote.service.PostService
import com.survivalcoding.network_apps.feature_paging.domain.model.Post

class RemotePostDataSource : PagingSource<Int, Post>() {
    private val service = RetrofitClient.getClient().create(PostService::class.java)

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Post> {
        return try {
            val next = params.key ?: 1
            val response = service.getPosts(page = next, pageSize = 20)

            LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = next + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Post>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}