package com.survivalcoding.network_apps.feature_paging.presentation.adpater

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.network_apps.databinding.LoadIndicatorBinding

class LoadIndicatorViewHolder(private val binding: LoadIndicatorBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(loadState: LoadState, onRetryButtonClick: () -> Unit) {
        when (loadState) {
            LoadState.DONE -> {
                binding.progressIndicator.visibility = View.GONE
                binding.retryButton.visibility = View.GONE
            }
            LoadState.LOADING -> {
                binding.progressIndicator.visibility = View.VISIBLE
                binding.retryButton.visibility = View.GONE
            }
            LoadState.FAIL -> {
                binding.progressIndicator.visibility = View.GONE
                binding.retryButton.visibility = View.VISIBLE
                binding.retryButton.setOnClickListener { onRetryButtonClick() }
            }
        }
    }

    companion object {
        fun from(parent: ViewGroup): LoadIndicatorViewHolder {
            return LoadIndicatorViewHolder(
                LoadIndicatorBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }
}

enum class LoadState {
    LOADING,
    DONE,
    FAIL
}