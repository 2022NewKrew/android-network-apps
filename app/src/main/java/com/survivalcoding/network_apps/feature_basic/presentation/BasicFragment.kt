package com.survivalcoding.network_apps.feature_basic.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.survivalcoding.network_apps.databinding.BasicFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BasicFragment : Fragment() {
    private var _binding: BasicFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: BasicViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = BasicFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.todo.observe(viewLifecycleOwner, { todo ->
            binding.resultTextView.text = todo.toString()
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}