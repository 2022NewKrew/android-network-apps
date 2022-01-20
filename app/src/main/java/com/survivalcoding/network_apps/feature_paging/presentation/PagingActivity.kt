package com.survivalcoding.network_apps.feature_paging.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import com.survivalcoding.network_apps.databinding.ActivityPagingBinding
import com.survivalcoding.network_apps.feature_paging.presentation.adapter.PostListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PagingActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityPagingBinding.inflate(layoutInflater)
    }
    private val viewModel by viewModels<PagingViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val adapter = PostListAdapter()
        val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        binding.recyclerViewPosts.addItemDecoration(decoration)

        binding.recyclerViewPosts.adapter = adapter

        lifecycleScope.launch {
            viewModel.pagingDataFlow.collect { adapter.submitData(it) }
        }

        lifecycleScope.launch {
            adapter.loadStateFlow.collectLatest { loadStates ->
                TODO("$loadStates 처리")
            }
        }

    }
}