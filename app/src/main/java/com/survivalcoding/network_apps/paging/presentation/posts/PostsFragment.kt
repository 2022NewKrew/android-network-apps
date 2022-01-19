package com.survivalcoding.network_apps.paging.presentation.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.survivalcoding.network_apps.databinding.FragmentPostsBinding
import com.survivalcoding.network_apps.paging.data.datasource.remote.PostRemoteDataSource
import com.survivalcoding.network_apps.paging.data.datasource.remote.RetrofitClient
import com.survivalcoding.network_apps.paging.data.repository.PostRepositoryImpl
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

        val recyclerView = binding.recylcerVIew
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val pagingAdapter = PostPagingDataAdapter()
        recyclerView.adapter = pagingAdapter

        lifecycleScope.launch {
            viewModel.posts?.collectLatest {
                pagingAdapter.submitData(it)
            }
            pagingAdapter.loadStateFlow.collectLatest {

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}