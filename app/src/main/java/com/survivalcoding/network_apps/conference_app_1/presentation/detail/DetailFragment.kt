package com.survivalcoding.network_apps.conference_app_1.presentation.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.survivalcoding.network_apps.R
import com.survivalcoding.network_apps.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.conference.observe(this) { conference ->
            binding.conferenceName.text = conference.name
            binding.country.text = conference.location
            binding.date.text = resources.getString(R.string.date, conference.start, conference.end)
            binding.link.text = conference.link
            binding.link.setOnClickListener {
                val webpage: Uri = Uri.parse(conference.link)
                val intent = Intent(Intent.ACTION_VIEW, webpage)
                if (intent.resolveActivity(requireActivity().packageManager) != null) {
                    startActivity(intent)
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}