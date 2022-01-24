package com.survivalcoding.network_apps.paging.ui.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadStateAdapter
import androidx.paging.map
import com.survivalcoding.network_apps.databinding.FragmentPostsBinding
import com.survivalcoding.network_apps.paging.ui.posts.adapter.loadstate.PostLoadStateAdapter
import com.survivalcoding.network_apps.paging.ui.posts.adapter.post.PostPagingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PostsFragment: Fragment() {

    private var _binding: FragmentPostsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PostsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPostsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Posts"

        val adapter = PostPagingAdapter { postId, isExpanded ->
            viewModel.updateIsExpandedMap(postId, isExpanded)
        }
        binding.postsRv.adapter = adapter.withLoadStateFooter(PostLoadStateAdapter(adapter::retry))

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collectLatest {
                    adapter.submitData(it.postItems)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}