package com.survivalcoding.network_apps.feature_paging.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.survivalcoding.network_apps.databinding.ActivityPostsBinding
import com.survivalcoding.network_apps.feature_paging.data.datasource.remote.RemotePostDataSource
import com.survivalcoding.network_apps.feature_paging.data.repository.PostRepositoryImpl
import com.survivalcoding.network_apps.feature_paging.presentation.adapter.PostListAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PostsActivity : AppCompatActivity() {
    private val viewModel by viewModels<PostsViewModel> {
        PostsViewModelFactory(PostRepositoryImpl(RemotePostDataSource()))
    }
    private val binding by lazy { ActivityPostsBinding.inflate(layoutInflater) }
    private val adapter by lazy { PostListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // recycler view 설정
        binding.postsRecyclerView.adapter = adapter

        viewModel.state.observe(this) { state ->
            // progress bar 설정
            binding.postsProgressBar.isVisible = state.isLoading
        }

        // post list 업데이트 관찰
        lifecycleScope.launch {
            viewModel.posts.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }
}