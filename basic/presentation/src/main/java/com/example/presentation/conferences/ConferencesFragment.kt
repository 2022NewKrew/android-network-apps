package com.example.presentation.conferences

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.domain.usecase.GetConferencesUseCase
import com.example.presentation.App
import com.example.presentation.LoadingPopUp
import com.example.presentation.R
import com.example.presentation.conferencedetail.ConferenceDetailFragment
import com.example.presentation.conferences.adapter.ConferenceListAdapter
import com.example.presentation.databinding.FragmentConferencesBinding

class ConferencesFragment : Fragment() {

    private val binding: FragmentConferencesBinding by lazy {
        FragmentConferencesBinding.inflate(layoutInflater)
    }

    private val adapter: ConferenceListAdapter by lazy {
        ConferenceListAdapter {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, ConferenceDetailFragment.newInstance(it))
                .addToBackStack(null)
                .commit()
        }
    }

    private val viewModel: ConferencesViewModel by lazy {
        ViewModelProvider(
            this,
            ConferencesViewModelFactory(GetConferencesUseCase((requireActivity().application as App).conferenceRepository))
        )[ConferencesViewModel::class.java]
    }

    private val loadingPopUp: LoadingPopUp by lazy {
        LoadingPopUp()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.conferencesRecyclerView.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.conferenceUiState.observe(viewLifecycleOwner) {
            if (it.isLoading) {
                loadingPopUp.show(parentFragmentManager, "loading_popup")
            } else {
                loadingPopUp.dismiss()
                adapter.submitList(it.conferenceList)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ConferencesFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}