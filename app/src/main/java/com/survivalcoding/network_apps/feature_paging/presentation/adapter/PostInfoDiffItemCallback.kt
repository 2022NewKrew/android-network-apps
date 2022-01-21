package com.survivalcoding.network_apps.feature_paging.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.survivalcoding.network_apps.feature_paging.domain.model.Post
import com.survivalcoding.network_apps.feature_paging.presentation.PostWithUserInfo

object PostInfoDiffItemCallback : DiffUtil.ItemCallback<PostWithUserInfo>() {
    override fun areItemsTheSame(oldItem: PostWithUserInfo, newItem: PostWithUserInfo): Boolean {
        return oldItem.post == newItem.post
    }

    override fun areContentsTheSame(oldItem: PostWithUserInfo, newItem: PostWithUserInfo): Boolean {
        return oldItem == newItem
    }
}