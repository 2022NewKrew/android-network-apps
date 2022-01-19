package com.survivalcoding.network_apps.feature_conference_app_1.ui.conferences.adapter

import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.network_apps.databinding.ConferenceListItemBinding
import com.survivalcoding.network_apps.feature_conference_app_1.domain.model.Conference

class ConferenceViewHolder(private val binding: ConferenceListItemBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(conference: Conference) {
        binding.name.text = conference.name
        binding.location.text = conference.location
    }
}