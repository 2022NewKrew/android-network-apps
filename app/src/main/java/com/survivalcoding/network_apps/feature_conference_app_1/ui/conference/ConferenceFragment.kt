package com.survivalcoding.network_apps.feature_conference_app_1.ui.conference

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.survivalcoding.network_apps.databinding.FragmentConferenceBinding
import com.survivalcoding.network_apps.feature_conference_app_1.domain.model.Conference

class ConferenceFragment: Fragment() {

    companion object {
        const val ARG_CONFERENCE_KEY = "conference"
    }

    private var _binding: FragmentConferenceBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentConferenceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireArguments().get(ARG_CONFERENCE_KEY) as? Conference)?.apply {
            (requireActivity() as AppCompatActivity).supportActionBar?.title = name

            binding.location.text = location
            binding.periodTv.text = "${start} ~ ${end}"
            binding.linkTv.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(link)))
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}