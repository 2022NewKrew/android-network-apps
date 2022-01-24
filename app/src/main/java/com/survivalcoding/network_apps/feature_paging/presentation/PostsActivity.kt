package com.survivalcoding.network_apps.feature_paging.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.google.android.material.snackbar.Snackbar
import com.survivalcoding.network_apps.R
import com.survivalcoding.network_apps.databinding.ActivityPostsBinding
import com.survivalcoding.network_apps.feature_paging.data.datasource.remote.RemotePostDataSource
import com.survivalcoding.network_apps.feature_paging.data.datasource.remote.RetrofitClient
import com.survivalcoding.network_apps.feature_paging.data.datasource.remote.service.PostService
import com.survivalcoding.network_apps.feature_paging.data.repository.PostRepositoryImpl
import com.survivalcoding.network_apps.feature_paging.presentation.adapter.PostListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PostsActivity : AppCompatActivity() {
    private val viewModel: PostsViewModel by viewModels()
    private val binding by lazy { ActivityPostsBinding.inflate(layoutInflater) }
    private val adapter by lazy { PostListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // recycler view 설정
        binding.postsRecyclerView.adapter = adapter

        lifecycleScope.launch {
            adapter.loadStateFlow.collectLatest {
                // loading progress bar 설정
                binding.postsProgressBar.isVisible = it.refresh is LoadState.Loading

                // error 발생 시 알림
                if (it.refresh::class == LoadState.Error::class) {
                    Snackbar.make(
                        binding.root,
                        getString(R.string.error_network),
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        }

        lifecycleScope.launch {
            // post list 업데이트 관찰
            viewModel.posts.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }
}