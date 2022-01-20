package com.survivalcoding.network_apps.paging.presentation.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.survivalcoding.network_apps.databinding.FragmentPostsBinding
import com.survivalcoding.network_apps.paging.data.datasource.remote.PostRemoteDataSource
import com.survivalcoding.network_apps.paging.data.datasource.remote.RetrofitClient
import com.survivalcoding.network_apps.paging.data.repository.PostRepositoryImpl
import com.survivalcoding.network_apps.paging.presentation.posts.adapter.PostPagingDataAdapter
import com.survivalcoding.network_apps.paging.presentation.posts.adapter.PostViewModel
import com.survivalcoding.network_apps.paging.presentation.posts.adapter.PostViewModelFactory
import com.survivalcoding.network_apps.paging.presentation.posts.adapter.PostsLoadStateAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PostsFragment : Fragment() {
    private var _binding: FragmentPostsBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<PostViewModel> {
        PostViewModelFactory(
            PostRepositoryImpl(
                PostRemoteDataSource(RetrofitClient.apiService)
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPostsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.recylcerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val pagingAdapter = PostPagingDataAdapter()
        recyclerView.adapter = pagingAdapter.withLoadStateHeaderAndFooter(
            header = PostsLoadStateAdapter { pagingAdapter.retry() },
            footer = PostsLoadStateAdapter { pagingAdapter.retry() }
        )
        pagingAdapter.addLoadStateListener { loadState ->
            val isListEmpty =
                loadState.refresh is LoadState.NotLoading && pagingAdapter.itemCount == 0
            binding.emptyList.isVisible = isListEmpty

            binding.recylcerView.isVisible = loadState.source.refresh is LoadState.NotLoading
            binding.progressBar.isVisible = loadState.source.refresh is LoadState.Loading
            binding.retryButton.isVisible = loadState.source.refresh is LoadState.Error

            val errorState = loadState.source.append as? LoadState.Error
                ?: loadState.source.prepend as? LoadState.Error
                ?: loadState.append as? LoadState.Error
                ?: loadState.prepend as? LoadState.Error
                ?: loadState.refresh as? LoadState.Error
            errorState?.let {
                Toast.makeText(
                    requireContext(),
                    "${it.error}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        binding.retryButton.setOnClickListener { pagingAdapter.retry() }

        lifecycleScope.launch {
            viewModel.posts.collectLatest {
                pagingAdapter.submitData(it)
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}