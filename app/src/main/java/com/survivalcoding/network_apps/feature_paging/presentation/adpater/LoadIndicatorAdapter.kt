package com.survivalcoding.network_apps.feature_paging.presentation.adpater

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class LoadIndicatorAdapter(private val onRetryButtonClick: () -> Unit) : RecyclerView.Adapter<LoadIndicatorViewHolder>() {

    private var loadState = LoadState.DONE

    fun setLoadState(loadState: LoadState) {
        this.loadState = loadState
        notifyItemChanged(0)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        LoadIndicatorViewHolder.from(parent)

    override fun onBindViewHolder(holder: LoadIndicatorViewHolder, position: Int) {
        holder.bind(loadState, onRetryButtonClick)
    }

    override fun getItemCount() = 1
}