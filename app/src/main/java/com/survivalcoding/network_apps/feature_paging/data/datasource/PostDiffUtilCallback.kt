package com.survivalcoding.network_apps.feature_paging.data.datasource

import androidx.recyclerview.widget.DiffUtil
import com.survivalcoding.network_apps.feature_paging.domain.model.PostItem

object PostDiffUtilCallback: DiffUtil.ItemCallback<PostItem>() {
    override fun areItemsTheSame(oldItem: PostItem, newItem: PostItem) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: PostItem, newItem: PostItem) = oldItem == newItem
}