package com.survivalcoding.network_apps.feature_paging.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.survivalcoding.network_apps.databinding.ActivityPostListBinding
import com.survivalcoding.network_apps.feature_paging.presentation.adpater.InfiniteScrollListener
import com.survivalcoding.network_apps.feature_paging.presentation.adpater.LoadIndicatorAdapter
import com.survivalcoding.network_apps.feature_paging.presentation.adpater.LoadState
import com.survivalcoding.network_apps.feature_paging.presentation.adpater.PostListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PostListActivity : AppCompatActivity() {

    private val viewModel: PostListViewModel by viewModels()

    private val binding: ActivityPostListBinding by lazy {
        ActivityPostListBinding.inflate(layoutInflater)
    }

    private val postListAdapter: PostListAdapter by lazy {
        PostListAdapter(15, object : InfiniteScrollListener {
            override fun load() {
                loadIndicatorAdapter.setLoadState(LoadState.LOADING)
                viewModel.loadNextPage(postListAdapter.itemCount)
            }
        })
    }

    private val loadIndicatorAdapter: LoadIndicatorAdapter by lazy {
        LoadIndicatorAdapter {
            viewModel.loadNextPage(postListAdapter.itemCount)
        }
    }

    private val concatAdapter: ConcatAdapter by lazy {
        ConcatAdapter(postListAdapter, loadIndicatorAdapter)
    }

    private var isFirstLoading = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        loadIndicatorAdapter.setLoadState(LoadState.LOADING)
        binding.postsRecyclerView.adapter = concatAdapter
        binding.postsRecyclerView.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )

        lifecycleScope.launch {
            viewModel.postListUiState.flowWithLifecycle(lifecycle).collectLatest {
                val recyclerView = binding.postsRecyclerView
                val lastCompletelyVisibleItemPosition = (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                val prevItemCount = postListAdapter.itemCount

                postListAdapter.submitList(it.postList) {
                    if (isFirstLoading && it.postList.isNotEmpty()) {
                        isFirstLoading = false
                        recyclerView.scrollToPosition(0)
                    } else if (lastCompletelyVisibleItemPosition == prevItemCount) {
                        recyclerView.scrollToPosition(lastCompletelyVisibleItemPosition)
                    }
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.eventFlow.collect {
                    loadIndicatorAdapter.setLoadState(LoadState.DONE)
                }
            }
        }
    }
}