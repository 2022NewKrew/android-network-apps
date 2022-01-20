package com.survivalcoding.network_apps.paging.domain.usecase

import com.survivalcoding.network_apps.paging.data.datasource.remote.PostApi
import com.survivalcoding.network_apps.paging.domain.model.User

class GetRemoteUserById(private val postApi: PostApi) {
    suspend operator fun invoke(id: Int): User {
        return postApi.getUserById(id)
    }
}