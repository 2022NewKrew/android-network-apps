package com.survivalcoding.network_apps.feature_conference_app_1.ui.conferences

import android.graphics.drawable.VectorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.fragment.app.viewModels
import com.survivalcoding.network_apps.R
import com.survivalcoding.network_apps.databinding.FragmentConferencesBinding
import com.survivalcoding.network_apps.feature_conference_app_1.domain.model.Conference
import com.survivalcoding.network_apps.feature_conference_app_1.ui.conference.ConferenceFragment
import com.survivalcoding.network_apps.feature_conference_app_1.ui.conferences.adapter.ConferenceAdapter
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConferencesFragment: Fragment() {

    private var _binding: FragmentConferencesBinding? = null
    private val binding get() = _binding!!

    private val conferencesViewModel: ConferencesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentConferencesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Conferences"

        val adapter = ConferenceAdapter { startConferenceFragment(it) }
        binding.conferencesRv.adapter = adapter

        conferencesViewModel.conferences.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun startConferenceFragment(conference: Conference) {
        requireActivity().supportFragmentManager.commit {
            setReorderingAllowed(false)
            val args = Bundle().apply {
                putParcelable(ConferenceFragment.ARG_CONFERENCE_KEY, conference)
            }
            replace<ConferenceFragment>(R.id.mainFcv, null, args)
            addToBackStack(null)
        }
    }
}