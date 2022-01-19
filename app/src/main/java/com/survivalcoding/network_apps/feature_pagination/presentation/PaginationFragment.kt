package com.survivalcoding.network_apps.feature_pagination.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.survivalcoding.network_apps.R
import com.survivalcoding.network_apps.databinding.FragmentPaginationBinding
import com.survivalcoding.network_apps.feature_pagination.data.datasource.remote.PageRemotePostItemDataSource
import com.survivalcoding.network_apps.feature_pagination.data.repository.PostItemsRepositoryImpl
import com.survivalcoding.network_apps.feature_pagination.presentation.adapter.PaginationListAdapter
import com.survivalcoding.network_apps.feature_pagination.presentation.adapter.PaginationListNotPageAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PaginationFragment : Fragment() {
    private var _binding: FragmentPaginationBinding? = null
    private val binding get() = _binding!!

    private val adapter by lazy {
        PaginationListAdapter(
            clickEvent = { postItem ->

            }
        )
    }

    private val notPagingAdapter by lazy {
        PaginationListNotPageAdapter(
            clickEvent = { postItem ->

            }
        )
    }

    private val viewModel by viewModels<PaginationViewModel> {
        PaginationViewModelFactory(PostItemsRepositoryImpl(PageRemotePostItemDataSource()))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPaginationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvPostsList.adapter = adapter

        lifecycleScope.launch {
            viewModel.p.collectLatest {
                adapter.submitData(it)
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}