package com.survivalcoding.network_apps.paging.data.datasource.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.survivalcoding.network_apps.paging.data.repository.PostRepositoryImpl.Companion.NETWORK_PAGE_SIZE
import com.survivalcoding.network_apps.paging.domain.model.Post
import retrofit2.HttpException
import java.io.IOException

class PostPagingSource(
    private val postApi: PostApi
) : PagingSource<Int, Post>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Post> {
        return try {
            // Start refresh at page 1 if undefined.
            val nextPageNumber = params.key ?: 1
            val response = postApi.getPosts(nextPageNumber, params.loadSize)

            LoadResult.Page(
                data = response,
                prevKey = if(nextPageNumber > 1) nextPageNumber - 1 else null,
                nextKey = nextPageNumber + (params.loadSize / NETWORK_PAGE_SIZE) //Todo: nextKey 처리 방법 강구
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
    override fun getRefreshKey(state: PagingState<Int, Post>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}