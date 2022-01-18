package com.survivalcoding.network_apps.conference_app_1.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.survivalcoding.network_apps.R
import com.survivalcoding.network_apps.conference_app_1.data.datasource.ConferenceRemoteDataSource
import com.survivalcoding.network_apps.conference_app_1.data.network.ConferencesApi
import com.survivalcoding.network_apps.conference_app_1.data.repository.ConferenceRepositoryImpl
import com.survivalcoding.network_apps.conference_app_1.presentation.detail.DetailFragment
import com.survivalcoding.network_apps.conference_app_1.presentation.list.adapter.ConferenceAdapter
import com.survivalcoding.network_apps.databinding.FragmentListBinding

class ListFragment : Fragment() {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private val viewModel by activityViewModels<ListViewModel> {
        ListViewModelFactory(
            requireActivity().application, ConferenceRepositoryImpl(
                ConferenceRemoteDataSource(ConferencesApi)
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = ConferenceAdapter(onClickView = {
            parentFragmentManager
                .beginTransaction()
                .replace(
                    R.id.fragment_container_view,
                    DetailFragment().apply {
                        this.arguments = bundleOf(CLICK to it)
                    })
                .addToBackStack(null)
                .commit()
        })
        recyclerView.adapter = adapter

        viewModel.conference.observe(this) {
            adapter.submitList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val CLICK = "click"
    }
}