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
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.survivalcoding.network_apps.MyApp
import com.survivalcoding.network_apps.R
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
            viewModel.eventHandler(TestEvent.RequestNet(id))
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
                        println((it.refresh as LoadState.Error).error.message)
                        Snackbar.make(view, "ERROR!!!", Snackbar.LENGTH_SHORT).show()
                    }
                }

                viewModel.state.collectLatest {
                    it.post?.collectLatest { posts ->
                        adapter.submitData(PagingData.empty())
                        println(it.users)
                        adapter.submitData(posts)
                    }

                }

/*                viewModel.state.post?.collectLatest { pagingData ->
                    adapter.submitData(pagingData)
                }

                viewModel. {
                    adapter.submitData()
                }*/
            }
        }
    }
}