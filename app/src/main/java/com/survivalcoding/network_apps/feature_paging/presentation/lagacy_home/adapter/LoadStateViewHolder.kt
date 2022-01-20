package com.survivalcoding.network_apps.feature_paging.presentation.lagacy_home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.network_apps.R
import com.survivalcoding.network_apps.databinding.ItemPhotoBinding

class LoadStateViewHolder(
    parent: ViewGroup,
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context)
        .inflate(R.layout.item_photo, parent, false)
) {
    private val binding = ItemPhotoBinding.bind(itemView)

    fun bind(loadState: LoadState) {
        binding.progressBar.isVisible = loadState is LoadState.Loading
        binding.photoImageView.isVisible = false
        binding.titleTextView.isVisible = false
    }
}