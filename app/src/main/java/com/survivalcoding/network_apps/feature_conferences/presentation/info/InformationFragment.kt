package com.survivalcoding.network_apps.feature_conferences.presentation.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.survivalcoding.network_apps.R
import com.survivalcoding.network_apps.feature_conferences.domain.model.Conference
import com.survivalcoding.network_apps.feature_conferences.presentation.list.ConferencesFragment
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

        val period = "${dateFormat(conference.start)} ~ ${dateFormat(conference.end)}"
        binding.tvConferenceInformationPeriod.text = period

        binding.tvConferenceInformationLink.text = conference.link
//        binding.tvConferenceInformationLink.setOnClickListener {
//            val url = conference.link
//            val intent = Intent(Intent.ACTION_VIEW)
//            intent.data = Uri.parse(url)
//            try {
//                startActivity(intent)
//            } catch (e: ActivityNotFoundException) {
//                Snackbar.make(binding.root, "실행할 수 있는 앱 없음.", Snackbar.LENGTH_SHORT)
//                    .show()
//            }
//        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun dateFormat(date: String): String {
        val split = date.split('-')
        val month = when (split[1].toInt()) {
            1 -> "Jan"
            2 -> "Feb"
            3 -> "Mar"
            4 -> "Apr"
            5 -> "May"
            6 -> "Jun"
            7 -> "Jul"
            8 -> "Aug"
            9 -> "Sep"
            10 -> "Oct"
            11 -> "Nov"
            else -> "Dec"
        }
        val day = if (split[2].length >= 2) split[2] else "0${split[2]}"
        return "$month $day, ${split[0]}"
    }
}