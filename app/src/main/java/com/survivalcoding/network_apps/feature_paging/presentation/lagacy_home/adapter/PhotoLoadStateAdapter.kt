package com.survivalcoding.network_apps.feature_paging.presentation.lagacy_home.adapter

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.survivalcoding.network_apps.feature_paging.presentation.lagacy_home.adapter.LoadStateViewHolder

class PhotoLoadStateAdapter : LoadStateAdapter<LoadStateViewHolder>() {
    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        return LoadStateViewHolder(parent)
    }
}