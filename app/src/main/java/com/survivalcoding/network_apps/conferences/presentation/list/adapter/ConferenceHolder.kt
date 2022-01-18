package com.survivalcoding.network_apps.conferences.presentation.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.network_apps.R
import com.survivalcoding.network_apps.conferences.domain.model.Conference
import com.survivalcoding.network_apps.databinding.ConferenceListItemBinding

class ConferenceHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.conference_list_item, parent, false)
) {
    private val binding = ConferenceListItemBinding.bind(itemView)

    fun binding(
        conference: Conference,
        clickEvent: (conference: Conference) -> Unit,
    ) {
        binding.conferenceItemTitle.text = conference.name
        binding.conferenceItemLocation.text = conference.location

        itemView.setOnClickListener {
            clickEvent(conference)
        }
    }
}