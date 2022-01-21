package com.survivalcoding.network_apps.feature_paging.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
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
                viewModel.loadNextPage()
            }
        }) {
            viewModel.toggleFoldedState(it)
        }
    }

    private val loadIndicatorAdapter: LoadIndicatorAdapter by lazy {
        LoadIndicatorAdapter {
            viewModel.loadNextPage()
            loadIndicatorAdapter.setLoadState(LoadState.LOADING)
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
                if (it.isError) {
                    Snackbar.make(binding.root, "네트워크 상태를 확인하세요", Snackbar.LENGTH_SHORT).show()
                    loadIndicatorAdapter.setLoadState(LoadState.FAIL)
                    return@collectLatest
                }

                if (it.isLoading) loadIndicatorAdapter.setLoadState(LoadState.LOADING)
                else loadIndicatorAdapter.setLoadState(LoadState.DONE)

                val recyclerView = binding.postsRecyclerView
                val lastCompletelyVisibleItemPosition =
                    (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                val prevItemCount = postListAdapter.itemCount

                postListAdapter.submitList(it.postList) {
                    if (isFirstLoading && it.postList.isNotEmpty()) {
                        isFirstLoading = false
                        recyclerView.scrollToPosition(0)
                    } else if (prevItemCount == lastCompletelyVisibleItemPosition) {
                        recyclerView.scrollToPosition(lastCompletelyVisibleItemPosition)
                    }
                }
            }
        }
    }
}