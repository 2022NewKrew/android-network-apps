package com.survivalcoding.network_apps.conference_app_1.presentation.conferences.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.network_apps.conference_app_1.domain.model.Conference
import com.survivalcoding.network_apps.databinding.ItemConferenceBinding

class ConferenceViewHolder(
    itemView: View,
    val onClickView: (Conference) -> Unit
) :
    RecyclerView.ViewHolder(itemView) {

    private val binding = ItemConferenceBinding.bind(itemView)
    fun bind(conference: Conference) {
        binding.conferenceTitle.text = conference.name
        binding.conferenceCountry.text = conference.location

        itemView.setOnClickListener {
            onClickView(conference)
        }
    }
}