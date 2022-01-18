package com.survivalcoding.network_apps.conferences.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.survivalcoding.network_apps.App
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
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}