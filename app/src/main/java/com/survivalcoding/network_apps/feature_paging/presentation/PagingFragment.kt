package com.survivalcoding.network_apps.feature_paging.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import androidx.paging.map
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.survivalcoding.network_apps.MyApp
import com.survivalcoding.network_apps.R
import com.survivalcoding.network_apps.feature_paging.domain.model.User
import com.survivalcoding.network_apps.feature_paging.presentation.adapter.PostInfoListAdapter
import com.survivalcoding.network_apps.feature_paging.presentation.util.PostInfoViewModelProvider
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PagingFragment : Fragment(R.layout.fragment_paging) {
    companion object {
        val REQUEST_KEY = "REQUEST_KEY"
        val BUNDLE_KEY = "BUNDLE_KEY"
    }

    private val viewModel by viewModels<PostInfoViewModel> {
        PostInfoViewModelProvider(
            (requireActivity().application as MyApp).postRepository,
            (requireActivity().application as MyApp).userRepository
        )
    }
    private val adapter = PostInfoListAdapter(
        userFromCache = { id -> viewModel.getUserFromCache(id) },
        userFromNet = { id ->
            {
                try {
                    viewModel.getUserFromNet(id)
                    viewModel.state.users[id]
                } catch (e: Exception) {
                    null
                }
            }

        }
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_paging, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)
        val recyclerView = view.findViewById<RecyclerView>(R.id.paging_recyclerview)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter



        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                adapter.addLoadStateListener {
                    if (it.refresh is LoadState.Error) {
                        Snackbar.make(view, "ERROR!!!", Snackbar.LENGTH_SHORT).show()
                    }
                }
                viewModel.state.post?.collectLatest { pagingData ->
                    adapter.submitData(pagingData)
/*                   뭔가 꼬였다.
 adapter.snapshot().forEach { post ->
                        post?.userId?.let { userId ->
                            val userInfo: User? = viewModel.getUserFromCache(userId)
                            if (userInfo != null) {
                                post.userName = userInfo.username
                            } else {

                            }
                        }

                    }*/
                }
            }
        }
    }
}