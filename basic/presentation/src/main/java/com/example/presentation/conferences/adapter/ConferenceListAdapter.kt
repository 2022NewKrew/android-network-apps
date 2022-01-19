package com.example.presentation.conferences.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.domain.model.Conference

class ConferenceListAdapter(private val onItemClick: (Conference) -> Unit) :
    ListAdapter<Conference, ConferenceViewHolder>(ConferenceDiffUtilItemCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ConferenceViewHolder.from(parent)

    override fun onBindViewHolder(holder: ConferenceViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClick)
    }
}