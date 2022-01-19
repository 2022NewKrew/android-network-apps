package com.survivalcoding.network_apps.feature_conferences.presentation.conferences.adapter

import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.network_apps.databinding.ItemConferenceBinding
import com.survivalcoding.network_apps.feature_conferences.domain.model.Conference

class ConferenceViewHolder(
    private val binding: ItemConferenceBinding,
    private val itemClickListener: (Conference) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(conference: Conference) {
        binding.conferenceTvName.text = conference.name
        binding.conferenceTvLocation.text = conference.location
        binding.root.setOnClickListener { itemClickListener(conference) }
    }
}