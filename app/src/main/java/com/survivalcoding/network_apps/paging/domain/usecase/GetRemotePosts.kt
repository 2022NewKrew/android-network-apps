package com.survivalcoding.network_apps.paging.domain.usecase

import com.survivalcoding.network_apps.paging.data.datasource.remote.PostApi
import com.survivalcoding.network_apps.paging.domain.model.Post

class GetRemotePosts(private val postApi: PostApi) {
    suspend operator fun invoke(page: Int, limit: Int): List<Post>{
        return postApi.getPosts(page, limit)
    }
}