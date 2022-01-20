package com.survivalcoding.network_apps.paging.domain.usecase

import com.survivalcoding.network_apps.paging.domain.model.PostWithName

class GetListOfPostWithName(
    private val getRemotePosts: GetRemotePosts,
    private val getRemoteUserById: GetRemoteUserById
) {
    suspend operator fun invoke(page: Int, limit: Int) : List<PostWithName>{
        val posts = getRemotePosts(page, limit)
        val userMap = mutableMapOf<Int, String>()
        val response = mutableListOf<PostWithName>()

        for (post in posts) {
            val name =
                if (post.userId in userMap) userMap[post.userId]
                else getRemoteUserById(post.userId).name
            name?.let { userMap[post.userId] = name }
            response.add(
                PostWithName(
                    body = post.body,
                    title = post.title,
                    id = post.id,
                    user = name
                )
            )
        }
        return response
    }
}