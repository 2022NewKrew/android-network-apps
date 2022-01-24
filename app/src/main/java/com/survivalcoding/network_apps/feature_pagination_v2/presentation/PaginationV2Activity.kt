package com.survivalcoding.network_apps.feature_pagination_v2.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.survivalcoding.network_apps.App
import com.survivalcoding.network_apps.R
import com.survivalcoding.network_apps.databinding.ActivityPaginationV2Binding
import com.survivalcoding.network_apps.feature_pagination_v2.presentation.adapter.InfiniteScrollListener
import com.survivalcoding.network_apps.feature_pagination_v2.presentation.adapter.PostListAdapter

class PaginationV2Activity : AppCompatActivity() {
    private val binding by lazy { ActivityPaginationV2Binding.inflate(layoutInflater) }
    private val viewModel by viewModels<PaginationV2ViewModel> {
        PaginationV2ViewModelFactory((application as App).paginationV2Repository)
    }
    private val listAdapter by lazy {
        PostListAdapter(15, object : InfiniteScrollListener {
            override fun load() {
                viewModel.loadPage()
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.rvPostsList.adapter = listAdapter
        viewModel.postItem.observe(this) { postItems ->
            listAdapter.submitList(postItems)
        }

        viewModel.state.observe(this) { currentState ->
            when (currentState) {
                State.EndLoading -> {}
                is State.Error -> {}
                State.Init -> viewModel.loadPage()
                State.Loading -> {
                    binding.paginationProgressBar.isVisible = true
                }
                State.NotLoading -> {
                    binding.paginationProgressBar.isVisible = false
                }
            }
        }
    }
}