package com.survivalcoding.network_apps.feature_paging.presentation.lagacy_home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.survivalcoding.network_apps.R
import com.survivalcoding.network_apps.databinding.ItemPhotoBinding
import com.survivalcoding.network_apps.feature_paging.domain.model.Photo

class PhotoViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context)
        .inflate(R.layout.item_photo, parent, false)
) {
    private val binding = ItemPhotoBinding.bind(itemView)

    fun bind(photo: Photo) {
        binding.progressBar.visibility = View.INVISIBLE
        binding.titleTextView.text = photo.title
        binding.photoImageView.load(photo.thumbnailUrl)
    }
}