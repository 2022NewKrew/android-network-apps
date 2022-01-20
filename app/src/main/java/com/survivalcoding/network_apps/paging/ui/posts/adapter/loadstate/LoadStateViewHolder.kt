package com.survivalcoding.network_apps.paging.ui.posts.adapter.loadstate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.network_apps.R
import com.survivalcoding.network_apps.databinding.ListItemLoadStateBinding

class LoadStateViewHolder(parent: ViewGroup, private val retry: () -> Unit) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(
        R.layout.list_item_load_state, parent, false
    )
) {
    private val binding = ListItemLoadStateBinding.bind(itemView)

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            binding.errorTv.text = loadState.error.localizedMessage
        }
        binding.errorTv.isVisible = loadState is LoadState.Error
        binding.progressBar.isVisible = loadState is LoadState.Loading
        binding.retryBt.isVisible = loadState is LoadState.Error
        binding.retryBt.setOnClickListener { retry() }
    }
}