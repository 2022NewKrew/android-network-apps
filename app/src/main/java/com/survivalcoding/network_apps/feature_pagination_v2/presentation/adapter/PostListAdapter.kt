package com.survivalcoding.network_apps.feature_pagination_v2.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.network_apps.feature_pagination.domain.model.PostItem

class PostListAdapter(
    private val threshold: Int,
    private val infiniteScrollListener: InfiniteScrollListener,
) : ListAdapter<PostItem, PostListHolder>(object : DiffUtil.ItemCallback<PostItem>() {
    override fun areItemsTheSame(oldItem: PostItem, newItem: PostItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PostItem, newItem: PostItem): Boolean {
        return oldItem == newItem
    }
}) {
    private val onScrollListener = object : RecyclerView.OnScrollListener() {
        private var isScrollingUp = false
        private var prevItemCount = 0

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)

            if (isScrollingUp) {
                val layoutManager = (recyclerView.layoutManager as LinearLayoutManager)
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()

                if (lastVisibleItemPosition >= layoutManager.itemCount - 1 - threshold) {
                    if (0 < prevItemCount && prevItemCount == recyclerView.adapter?.itemCount ?: return) return
                    prevItemCount = recyclerView.adapter?.itemCount ?: return
                    infiniteScrollListener.load()
                }
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            isScrollingUp = dy > 0
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostListHolder {
        return PostListHolder(parent)
    }

    override fun onBindViewHolder(holder: PostListHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        recyclerView.addOnScrollListener(onScrollListener)
    }
}

interface InfiniteScrollListener {
    fun load()
}