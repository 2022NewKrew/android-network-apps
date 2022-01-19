package com.survivalcoding.network_apps.feature_conference.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.network_apps.MyApp
import com.survivalcoding.network_apps.R
import com.survivalcoding.network_apps.feature_conference.presentation.adapter.ConferenceListAdapter
import com.survivalcoding.network_apps.feature_conference.presentation.util.ConferenceViewModelProvider

class ConferenceFragment : Fragment(R.layout.fragment_conference) {
    companion object {
        val REQUEST_KEY = "REQUEST_KEY"
        val BUNDLE_KEY = "BUNDLE_KEY"
    }

    private val viewModel by viewModels<ConferenceViewModel> {
        ConferenceViewModelProvider((requireActivity().application as MyApp).conferenceRepository)
    }
    private val adapter = ConferenceListAdapter {
        setFragmentResult(REQUEST_KEY, bundleOf(BUNDLE_KEY to it))
        parentFragmentManager.commit {
            replace<DetailFragment>(R.id.fragment_container_view)
            addToBackStack(null)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_conference, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val resultTextView = view.findViewById<TextView>(R.id.result_text_view)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)
        val recyclerView = view.findViewById<RecyclerView>(R.id.conference_recyclerview)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        viewModel.state.observe(viewLifecycleOwner, { state ->
            progressBar.isVisible = state.isLoading
            state.conferenceInfo?.let { items ->
                adapter.submitList(items)
            }
        })
    }
}