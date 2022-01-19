package com.survivalcoding.network_apps.paging.data.datasource.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.survivalcoding.network_apps.paging.data.datasource.remote.PostRemoteDataSource.Companion.NETWORK_PAGE_SIZE
import com.survivalcoding.network_apps.paging.domain.model.PostWithName
import retrofit2.HttpException
import java.io.IOException

class PostPagingSource(
    private val postApi: PostApi
) : PagingSource<Int, PostWithName>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PostWithName> {
        return try {
            val nextPageNumber = params.key ?: 1
            val posts = postApi.getPosts(nextPageNumber, NETWORK_PAGE_SIZE)
            val users = postApi.getUsers()

            val response = mutableListOf<PostWithName>()
            for (post in posts) {
                response.add(
                    PostWithName(
                        body = post.body,
                        title = post.title,
                        id = post.id,
                        user =
                        users.first { it.id == post.userId }.name
                    )
                )
            }

            LoadResult.Page(
                data = response,
                prevKey = if (nextPageNumber > 1) nextPageNumber - 1 else null,
                nextKey = if (response.isEmpty()) null else nextPageNumber + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PostWithName>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}