package com.survivalcoding.network_apps.feature_conferences.presentation.conferences

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.survivalcoding.network_apps.R
import com.survivalcoding.network_apps.databinding.FragmentConferencesBinding
import com.survivalcoding.network_apps.feature_conferences.data.datasource.remote.RemoteConferenceDataSource
import com.survivalcoding.network_apps.feature_conferences.data.repository.ConferenceRepositoryImpl
import com.survivalcoding.network_apps.feature_conferences.presentation.ConferencesViewModel
import com.survivalcoding.network_apps.feature_conferences.presentation.ConferencesViewModelFactory
import com.survivalcoding.network_apps.feature_conferences.presentation.conferences.adapter.ConferenceListAdapter
import com.survivalcoding.network_apps.feature_conferences.presentation.detail.DetailFragment

class ConferencesFragment : Fragment() {
    private var _binding: FragmentConferencesBinding? = null
    private val binding get() = _binding!!
    private val viewModel by activityViewModels<ConferencesViewModel> {
        ConferencesViewModelFactory(ConferenceRepositoryImpl(RemoteConferenceDataSource()))
    }
    private val adapter by lazy {
        ConferenceListAdapter { conference ->
            viewModel.selectConference(conference)
            // 상세 화면으로 이동
            parentFragmentManager.commit {
                replace<DetailFragment>(
                    R.id.fragment_container_view
                )
                setReorderingAllowed(true)
                addToBackStack(null)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConferencesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // actionBar 설정
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Conferences"

        // recyclerView 설정
        binding.conferencesRecyclerView.adapter = adapter

        viewModel.state.observe(viewLifecycleOwner) { state ->
            // conference list 업데이트 관찰
            adapter.submitList(state.conferences)

            // progressBar 설정
            binding.conferencesProgressBar.isVisible = state.isLoading
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}