package com.survivalcoding.network_apps.paging.domain.usecase

import com.survivalcoding.network_apps.paging.domain.model.Post
import com.survivalcoding.network_apps.paging.domain.model.PostWithName

class GetPostWithName(
    private val getRemoteUserById: GetRemoteUserById
) {
    suspend operator fun invoke(
        userMap: MutableMap<Int, String>,
        post: Post
    ): PostWithName {
        val name = userMap[post.userId] ?: getRemoteUserById(post.userId)?.name
        if (!userMap.contains(post.userId) && getRemoteUserById(post.userId) != null)
            userMap[post.userId] = getRemoteUserById(post.userId)!!.name

        return PostWithName(title = post.title, body = post.body, id = post.id, user = name)
    }
}