package com.survivalcoding.network_apps.feature_conferences.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.survivalcoding.network_apps.App
import com.survivalcoding.network_apps.R
import com.survivalcoding.network_apps.databinding.FragmentConferencesBinding
import com.survivalcoding.network_apps.feature_conferences.presentation.info.InformationFragment
import com.survivalcoding.network_apps.feature_conferences.presentation.list.adapter.ConferenceAdapter
import com.survivalcoding.network_apps.feature_conferences.presentation.util.ConferencesViewModelProvider

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

        viewModel.error.observe(viewLifecycleOwner) { error ->
            error?.let { Toast.makeText(requireContext(), "$error", Toast.LENGTH_SHORT).show() }
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