package com.example.presentation.conferencedetail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.domain.convertDateString
import com.example.domain.model.Conference
import com.example.presentation.R
import com.example.presentation.databinding.FragmentConferenceDetailBinding

class ConferenceDetailFragment : Fragment() {

    private val binding: FragmentConferenceDetailBinding by lazy {
        FragmentConferenceDetailBinding.inflate(layoutInflater)
    }

    private val viewModel: ConferenceDetailViewModel by lazy {
        ViewModelProvider(
            this,
            ConferenceDetailViewModelFactory()
        )[ConferenceDetailViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val conference = it.getSerializable(CONFERENCE) as Conference
            viewModel.setConference(conference)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.conference.observe(viewLifecycleOwner) { conference ->
            binding.nameTextView.text = conference.name
            binding.locationTextView.text = conference.location
            binding.dateTextView.text = getString(
                R.string.period,
                convertDateString(conference.start ?: ""),
                convertDateString(conference.end ?: "")
            )
            binding.websiteLinkTextView.setOnClickListener {
                Intent(Intent.ACTION_VIEW, Uri.parse(conference.link)).apply {
                    if (resolveActivity(requireActivity().packageManager) != null) {
                        startActivity(this)
                    }
                }
            }
        }
    }

    companion object {
        private const val CONFERENCE = "conference"

        @JvmStatic
        fun newInstance(conference: Conference) =
            ConferenceDetailFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(CONFERENCE, conference)
                }
            }
    }
}