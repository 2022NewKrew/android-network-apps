package com.survivalcoding.network_apps.feature_paging.presentation.lagacy_home.adapter

import androidx.recyclerview.widget.DiffUtil
import com.survivalcoding.network_apps.feature_paging.domain.model.Photo

object PhotoDiffItemCallback : DiffUtil.ItemCallback<Photo>() {

    override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem == newItem
    }
}