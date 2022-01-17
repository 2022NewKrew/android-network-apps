package com.survivalcoding.network_apps.feature_basic.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.survivalcoding.network_apps.App
import com.survivalcoding.network_apps.R
import com.survivalcoding.network_apps.databinding.FragmentBasicBinding
import com.survivalcoding.network_apps.feature_basic.presentation.util.BasicViewModelProvider

class BasicFragment : Fragment(R.layout.fragment_basic) {
    private var _binding: FragmentBasicBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<BasicViewModel> {
        BasicViewModelProvider((requireActivity().application as App).basicRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBasicBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ibPreviousTodoButton.setOnClickListener {
            viewModel.onEvent(BasicEvent.MoveToPreviousTodo)
        }

        binding.ibFollowingTodoButton.setOnClickListener {
            viewModel.onEvent(BasicEvent.MoveToFollowingTodo)
        }

        viewModel.state.observe(viewLifecycleOwner, { state ->
            binding.progressBar.isVisible = state.isLoading
            binding.clData.isVisible = !state.isLoading

            binding.tvUseridInput.text = state.todo.userId.toString()
            binding.tvIdInput.text = state.todo.id.toString()
            binding.tvTitleInput.text = state.todo.title
            binding.tvCompletedInput.text = state.todo.completed.toString()
        })
    }
}