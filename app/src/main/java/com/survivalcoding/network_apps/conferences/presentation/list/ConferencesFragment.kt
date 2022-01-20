package com.survivalcoding.network_apps.conferences.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.survivalcoding.network_apps.App
import com.survivalcoding.network_apps.R
import com.survivalcoding.network_apps.conferences.presentation.info.InformationFragment
import com.survivalcoding.network_apps.conferences.presentation.list.adapter.ConferenceAdapter
import com.survivalcoding.network_apps.conferences.presentation.util.ConferencesViewModelProvider
import com.survivalcoding.network_apps.databinding.FragmentConferencesBinding

class ConferencesFragment : Fragment() {
    private var _binding: FragmentConferencesBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<ConferencesViewModel> {
        ConferencesViewModelProvider((requireActivity().application as App).conferencesRepository)
    }

    private val adapter by lazy {
        ConferenceAdapter(
            clickEvent = { conference ->
                val bundle = Bundle().apply {
                    putParcelable(KEY_CONFERENCE, conference)
                }
                parentFragmentManager.commit {
                    replace(R.id.fragment_container_view_conference, InformationFragment().apply {
                        arguments = bundle
                    })
                    setReorderingAllowed(true)
                    addToBackStack(null)
                }
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConferencesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvConferencesList.adapter = adapter

        viewModel.state.observe(viewLifecycleOwner) { state ->
            binding.progressBar.isVisible = state.isLoading
            adapter.submitList(state.conferences)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        const val KEY_CONFERENCE = "key_conference"
    }
}