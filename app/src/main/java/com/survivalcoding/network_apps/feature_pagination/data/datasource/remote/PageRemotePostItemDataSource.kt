package com.survivalcoding.network_apps.feature_pagination.data.datasource.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.survivalcoding.network_apps.feature_pagination.data.datasource.remote.service.PageResourceService
import com.survivalcoding.network_apps.feature_pagination.domain.model.Post
import com.survivalcoding.network_apps.feature_pagination.domain.model.User

class PageRemotePostItemDataSource(
    private val service: PageResourceService
) : PagingSource<Int, Post>(), PageRemoteDataSource {

    override fun getRefreshKey(state: PagingState<Int, Post>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Post> {
        return try {
            val pageIndex = params.key ?: 1
            val response = service.getPosts(page = pageIndex, pageSize = 20)
            LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = pageIndex + 1,
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override suspend fun getUsers(): List<User> {
        return service.getUsers()
    }

    override suspend fun getPosts(): List<Post> {
        return service.getPostsNotPage()
    }
}