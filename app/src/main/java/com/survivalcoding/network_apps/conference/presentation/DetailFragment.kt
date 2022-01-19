package com.survivalcoding.network_apps.conference.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import com.survivalcoding.network_apps.R
import com.survivalcoding.network_apps.conference.domain.model.ConferenceInfo
import com.survivalcoding.network_apps.databinding.FragmentDetailBinding
import android.content.Intent
import android.net.Uri


class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var cnfItem: ConferenceInfo? = null
        setFragmentResultListener(ConferenceFragment.REQUEST_KEY) { _, bundle ->
            cnfItem = bundle.getParcelable(ConferenceFragment.BUNDLE_KEY)
            cnfItem?.let {
                binding.detailName.text = it.name
                binding.detailLocation.text = it.location
                val range = it.start + " - " + it.end
                binding.detailRange.text = range
                binding.detailLink.text = it.link
            }
        }
        binding.detailLink.setOnClickListener {
            if (binding.detailLink.text.isNotEmpty()) {
                val intent =
                    Intent(Intent.ACTION_VIEW, Uri.parse(binding.detailLink.text.toString()))
                startActivity(intent)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}