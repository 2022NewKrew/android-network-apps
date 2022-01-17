package com.survivalcoding.network_apps.conference.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.survivalcoding.network_apps.MyApp
import com.survivalcoding.network_apps.R
import com.survivalcoding.network_apps.conference.presentation.util.ConferenceViewModelProvider

class ConferenceFragment : Fragment(R.layout.fragment_conference) {
    private val viewModel by viewModels<ConferenceViewModel> {
        ConferenceViewModelProvider((requireActivity().application as MyApp).conferenceRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_conference, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val resultTextView = view.findViewById<TextView>(R.id.result_text_view)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)

        viewModel.state.observe(viewLifecycleOwner, { state ->
            progressBar.isVisible = state.isLoading
            state.conferenceInfo?.let { items ->
                resultTextView.text = items[0].toString()
            }
        })
    }

}