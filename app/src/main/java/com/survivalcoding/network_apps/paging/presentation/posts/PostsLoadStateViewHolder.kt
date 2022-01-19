package com.survivalcoding.network_apps.paging.presentation.posts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.network_apps.R
import com.survivalcoding.network_apps.databinding.ItemPostsLoadStateFooterViewBinding

class PostsLoadStateViewHolder(private val binding: ItemPostsLoadStateFooterViewBinding, retry: () -> Unit) :
    RecyclerView.ViewHolder(binding.root) {

    init {
        binding.retryButton.setOnClickListener { retry.invoke() }
    }

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            binding.errorMsg.text = loadState.error.localizedMessage
        }
        binding.progressBar.isVisible = loadState is LoadState.Loading
        binding.retryButton.isVisible = loadState is LoadState.Error
        binding.errorMsg.isVisible = loadState is LoadState.Error
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): PostsLoadStateViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_posts_load_state_footer_view, parent, false)
            val binding = ItemPostsLoadStateFooterViewBinding.bind(view)
            return PostsLoadStateViewHolder(binding, retry)
        }
    }
}