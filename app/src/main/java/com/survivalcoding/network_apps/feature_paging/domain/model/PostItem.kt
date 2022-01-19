package com.survivalcoding.network_apps.feature_paging.domain.model

data class PostItem(val id: Int, val username: String, val title: String, val body: String) {
    constructor(postCacheItem: PostCacheItem, userCacheItem: UserCacheItem) : this(
        postCacheItem.id,
        userCacheItem.username,
        postCacheItem.title,
        postCacheItem.body
    )
}
