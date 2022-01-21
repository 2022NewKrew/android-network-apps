package com.survivalcoding.network_apps.feature_paging.data.datasource

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.survivalcoding.network_apps.feature_paging.domain.model.PostCacheItem
import com.survivalcoding.network_apps.feature_paging.domain.model.PostItem
import com.survivalcoding.network_apps.feature_paging.domain.model.UserCacheItem
import com.survivalcoding.network_apps.feature_paging.domain.repository.NetworkResult
import com.survivalcoding.network_apps.feature_paging.domain.repository.PostPagingConstants.REQUEST_PAGE_SIZE
import com.survivalcoding.network_apps.feature_paging.domain.repository.PostPagingConstants.STARTING_PAGE_INDEX
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostDataSource @Inject constructor(private val postsService: PostService) :
    PagingSource<Int, PostItem>() {
    private suspend fun getPosts(page: Int, limit: Int = 1): List<PostCacheItem>? {
        return when (val response = safeApiCall { postsService.getPosts(page = page, limit = limit) }) {
            is NetworkResult.Success -> {
                response.data
            }
            is NetworkResult.Error -> {
                null
            }
        }
    }

    private suspend fun getUserById(id: Int): UserCacheItem? {
        return when (val response = safeApiCall { postsService.getUserById(id) }) {
            is NetworkResult.Success -> {
                response.data
            }
            is NetworkResult.Error -> {
                null
            }
        }
    }

    private suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): NetworkResult<T> {
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return NetworkResult.Success(body)
                }
            }
            return error("${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(errorMessage: String): NetworkResult<T> =
        NetworkResult.Error("Api call failed $errorMessage")

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PostItem> {
        val position = params.key ?: STARTING_PAGE_INDEX
        return try {
            val posts = getPosts(position, params.loadSize)

            val retVal = mutableListOf<PostItem>()

            posts?.forEach { postCacheItem ->
                val user = getUserById(postCacheItem.userId)
                user?.let { userCacheItem -> retVal.add(PostItem(postCacheItem, userCacheItem)) }
            }

            val nextKey = if (posts.isNullOrEmpty()) {
                null
            } else {
                position + (params.loadSize / REQUEST_PAGE_SIZE)
            }
            LoadResult.Page(
                data = retVal,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position,
                nextKey = nextKey
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PostItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}