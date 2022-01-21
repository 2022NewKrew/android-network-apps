package com.survivalcoding.network_apps.feature_paging.presentation.adpater

import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.network_apps.feature_paging.presentation.PostItem

class PostListAdapter(
    private val firstPageIndex: Int,
    private val pageSize: Int,
    private val threshold: Int,
    private val infiniteScrollListener: InfiniteScrollListener,
    private val onBodyTextClick: (Int) -> Unit
) :
    ListAdapter<PostItem, PostItemViewHolder>(PostItemDiffUtilItemCallback()) {

    val lastPage get() = itemCount / pageSize + firstPageIndex
    private var _lastRequestedPage = lastPage
    val lastRequestedPage get() = _lastRequestedPage

    private val onScrollListener = object : RecyclerView.OnScrollListener() {

        private var isScrollingUp = false

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)

            if (isScrollingUp) {
                if (_lastRequestedPage > lastPage) return // 불러와야 할 페이지를 아직 불러오지 못한 경우 return

                val lastVisibleItemPosition =
                    (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()

                if (_lastRequestedPage * pageSize - lastVisibleItemPosition <= threshold) { // 다음에 불러와야 할 페이지 직전에 왔을 때만 load 호출되도록 한다
                    infiniteScrollListener.load(++_lastRequestedPage)
                }
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            isScrollingUp = dy > 0
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PostItemViewHolder.from(parent)

    override fun onBindViewHolder(holder: PostItemViewHolder, position: Int) {
        holder.bind(getItem(position), onBodyTextClick)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        recyclerView.addOnScrollListener(onScrollListener)
    }
}

interface InfiniteScrollListener {
    fun load(page: Int)
}