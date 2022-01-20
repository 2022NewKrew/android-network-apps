package com.survivalcoding.network_apps.feature_paging.presentation.lagacy_home.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.survivalcoding.network_apps.feature_paging.domain.model.Photo

class PhotoListAdapter : PagingDataAdapter<Photo, PhotoViewHolder>(PhotoDiffItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(parent)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item!!)
    }
}