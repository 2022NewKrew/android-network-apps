package com.survivalcoding.network_apps.feature_paging.presentation.adpater

import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.network_apps.feature_paging.presentation.PostItem

class PostListAdapter(val threshold: Int, val infiniteScrollListener: InfiniteScrollListener) : ListAdapter<PostItem, PostItemViewHolder>(PostItemDiffUtilItemCallback()) {

    private val onScrollListener = object : RecyclerView.OnScrollListener() {

        private var isScrollingUp = false

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)

            if (isScrollingUp) {
                val lastVisibleItemPosition =
                    (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()

                if (lastVisibleItemPosition >= (recyclerView.layoutManager as LinearLayoutManager).itemCount - 1 - threshold) {
                    infiniteScrollListener.load()
                }
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            if (dy > 0) isScrollingUp = true
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PostItemViewHolder.from(parent)

    override fun onBindViewHolder(holder: PostItemViewHolder, position: Int) {
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