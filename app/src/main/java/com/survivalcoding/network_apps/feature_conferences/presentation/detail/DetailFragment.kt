package com.survivalcoding.network_apps.feature_conferences.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import com.survivalcoding.network_apps.feature_conferences.data.repository.ConferenceRepositoryImpl
import com.survivalcoding.network_apps.feature_conferences.presentation.ConferencesViewModel
import com.survivalcoding.network_apps.feature_conferences.presentation.ConferencesViewModelFactory
import android.content.Intent
import android.net.Uri
import com.survivalcoding.network_apps.databinding.FragmentDetailBinding
import com.survivalcoding.network_apps.feature_conferences.data.datasource.remote.RemoteConferenceDataSource

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel by activityViewModels<ConferencesViewModel> {
        ConferencesViewModelFactory(ConferenceRepositoryImpl(RemoteConferenceDataSource()))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // actionBar 설정
        (requireActivity() as AppCompatActivity).supportActionBar?.title =
            viewModel.conferenceSelected.value?.name

        // 컨퍼런스 정보 표시
        viewModel.conferenceSelected.observe(this) { conference ->
            binding.detailTvLocation.text = conference?.location
            binding.detailTvDuration.text =
                getDurationStr(conference?.start, conference?.end)
        }

        // 링크 클릭 시 웹사이트로 이동
        binding.detailTvLink.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(viewModel.conferenceSelected.value?.link)
                )
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun getDurationStr(start: String?, end: String?): String {
        if (start == null || end == null) return ""

        var result = ""
        val startDate = start.split("-")
        result += ("${getMonth(startDate[1].toInt())} ${startDate[2]}, ${startDate[0]} - ")
        val endDate = end.split("-")
        result += ("${getMonth(endDate[1].toInt())} ${endDate[2]}, ${endDate[0]}")
        return result
    }

    private fun getMonth(month: Int): String {
        return when (month) {
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
    }
}