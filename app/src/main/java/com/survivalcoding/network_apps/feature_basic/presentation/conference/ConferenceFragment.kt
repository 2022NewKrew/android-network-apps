package com.survivalcoding.network_apps.feature_basic.presentation.conference

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.survivalcoding.network_apps.databinding.ConferenceFragmentBinding
import com.survivalcoding.network_apps.feature_basic.presentation.conference.adapter.ConferenceListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConferenceFragment : Fragment() {
    private var _binding: ConferenceFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ConferenceViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ConferenceFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ConferenceListAdapter {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
            startActivity(browserIntent)
        }
        binding.recyclerViewConferences.adapter = adapter

        viewModel.conferences.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}