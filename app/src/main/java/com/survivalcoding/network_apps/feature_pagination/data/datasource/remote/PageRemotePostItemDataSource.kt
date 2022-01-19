package com.survivalcoding.network_apps.feature_pagination.data.datasource.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.survivalcoding.network_apps.feature_pagination.data.datasource.remote.service.PageResourceService
import com.survivalcoding.network_apps.feature_pagination.data.repository.NETWORK_PAGE_SIZE
import com.survivalcoding.network_apps.feature_pagination.domain.model.PostItem

class PageRemotePostItemDataSource : PagingSource<Int, PostItem>() {

    private val service = PageRetrofitClient.getClient().create(PageResourceService::class.java)

    override fun getRefreshKey(state: PagingState<Int, PostItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PostItem> {
        return try {
            val pageIndex = params.key ?: 1
            val response = service.getPosts(page = pageIndex).map {
                PostItem(it, service.getUserById(it.userId).name)
            }
            val nextKey =
                if (response.isEmpty()) null else pageIndex + (params.loadSize / NETWORK_PAGE_SIZE)
            LoadResult.Page(
                data = response,
                prevKey = if (pageIndex == 1) null else pageIndex,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}