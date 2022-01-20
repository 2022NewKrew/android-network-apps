package com.survivalcoding.network_apps.paging.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.survivalcoding.network_apps.paging.domain.model.Post
import com.survivalcoding.network_apps.paging.domain.model.User
import com.survivalcoding.network_apps.paging.domain.repository.PostRepository
import java.lang.Exception

class PostPagingSource(private val postRepository: PostRepository): PagingSource<Int, Post>() {

    private val idToUserMap = mutableMapOf<Int, User>()

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Post> {
        return try {
            val key = params.key ?: 1
            val posts = postRepository.getPostsByPage(key, params.loadSize)

            posts.forEach {
                if(it.userId !in idToUserMap) {
                    idToUserMap[it.userId] = postRepository.getUserById(it.userId)
                }
            }

            LoadResult.Page(
                posts.map { it.copy(userName = idToUserMap[it.userId]?.username ?: "") },
                null,
                key + 1
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