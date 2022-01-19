package com.survivalcoding.network_apps.feature_paging.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.survivalcoding.network_apps.databinding.ActivityPostsBinding
import com.survivalcoding.network_apps.feature_paging.data.datasource.remote.RemotePostDataSource
import com.survivalcoding.network_apps.feature_paging.data.repository.PostRepositoryImpl
import com.survivalcoding.network_apps.feature_paging.presentation.adapter.PostListAdapter

class PostsActivity : AppCompatActivity() {
    private val viewModel by viewModels<PostsViewModel> {
        PostsViewModelFactory(PostRepositoryImpl(RemotePostDataSource()))
    }
    private val binding by lazy { ActivityPostsBinding.inflate(layoutInflater) }
    private val adapter by lazy {
        PostListAdapter { post ->
            Toast.makeText(this, post.id.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // recycler view 설정
        binding.postsRecyclerView.adapter = adapter

        viewModel.state.observe(this) { state ->
            // progress bar 설정
            binding.postsProgressBar.isVisible = state.isLoading

            // post list 업데이트 관찰
            adapter.submitList(state.posts)
        }
    }
}