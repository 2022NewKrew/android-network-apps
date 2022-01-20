package com.survivalcoding.network_apps.feature_pagination.presentation

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
import com.survivalcoding.network_apps.App
import com.survivalcoding.network_apps.databinding.FragmentPaginationBinding
import com.survivalcoding.network_apps.feature_pagination.presentation.adapter.PaginationListAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PaginationFragment : Fragment() {
    private var _binding: FragmentPaginationBinding? = null
    private val binding get() = _binding!!

    private val adapter by lazy { PaginationListAdapter() }

    private val viewModel by viewModels<PaginationViewModel> {
        PaginationViewModelFactory((requireActivity().application as App).paginationRepository)
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

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.postItems.collectLatest {
                adapter.submitData(it)
            }
        }

        adapter.addLoadStateListener { combinedLoadStates ->
            when (combinedLoadStates.source.refresh) {
                // 로딩 중이지 않을 때 (활성 로드 작업이 없고 에러가 없음)
                is LoadState.NotLoading -> {
                    // 활성 로드 작업이 없고 에러가 없음 & 로드할 수 없음
                    if (combinedLoadStates.append.endOfPaginationReached)
                        viewModel.setState(PaginationState.PostLoadingError)
                    else viewModel.setState(PaginationState.NotLoading)
                }
                LoadState.Loading -> viewModel.setState(PaginationState.Loading)
                is LoadState.Error -> viewModel.setState(PaginationState.PostLoadingError)
            }
        }

        binding.paginationListTitle.setOnClickListener {
            Toast.makeText(requireContext(), "${viewModel.state.value}", Toast.LENGTH_SHORT).show()
        }

        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                PaginationState.NotLoading -> {
                    binding.clUserLoadingError.isVisible = false
                    binding.paginationProgressBar.isVisible = false
                }
                PaginationState.PostLoadingError -> {
                    handleError(".. Post Loading Error ..") {
                        adapter.retry()
                    }
                }
                PaginationState.UserLoadingError -> {
                    handleError(".. User Loading Error ..") {
                        viewModel.setState(PaginationState.Loading)
                        viewModel.loadUser()
                        adapter.retry()
                    }
                }
                PaginationState.Loading -> {
                    binding.paginationProgressBar.isVisible = true
                }
                PaginationState.EndLoading -> {
                    binding.clUserLoadingError.isVisible = false
                    binding.paginationProgressBar.isVisible = false
                }
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun handleError(message: String, block: () -> Unit) {
        binding.clUserLoadingError.isVisible = true
        binding.paginationProgressBar.isVisible = false
        binding.tvUserLoadingErrorContext.text = message
        binding.ibUserLoadingErrorRefresh.setOnClickListener {
            binding.clUserLoadingError.isVisible = false
            block.invoke()
        }
    }
}