package com.survivalcoding.network_apps.conferences.presentation.info

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.survivalcoding.network_apps.R
import com.survivalcoding.network_apps.conferences.domain.model.Conference
import com.survivalcoding.network_apps.conferences.presentation.list.ConferencesFragment
import com.survivalcoding.network_apps.databinding.FragmentInformationBinding

class InformationFragment : Fragment() {
    private var _binding: FragmentInformationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInformationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val conference: Conference =
            requireArguments().getParcelable(ConferencesFragment.KEY_CONFERENCE) ?: Conference()

        binding.clBackToConferences.setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.fragment_container_view_conference, ConferencesFragment())
                setReorderingAllowed(true)
            }
        }
        binding.tvConferenceInformationTitle.text = conference.name
        binding.tvConferenceInformationLocation.text = conference.location

        val period = "${conference.start} ~ ${conference.end}"
        binding.tvConferenceInformationPeriod.text = period

        binding.tvConferenceInformationLink.text = conference.link
//        binding.tvConferenceInformationLink.setOnClickListener {
//            val url = conference.link
//            val intent = Intent(Intent.ACTION_VIEW)
//            intent.data = Uri.parse(url)
//            startActivity(intent)
//        }
    }
}