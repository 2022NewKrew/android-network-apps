package com.survivalcoding.network_apps.feature_basic.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.survivalcoding.network_apps.R
import com.survivalcoding.network_apps.feature_basic.presentation.util.BasicViewModelProvider
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BasicFragment : Fragment(R.layout.fragment_basic) {
    private val viewModel: BasicViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_basic, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val resultTextView = view.findViewById<TextView>(R.id.result_text_view)

        viewModel.todo.observe(viewLifecycleOwner, { todo ->
            resultTextView.text = todo.toString()
        })
    }

}