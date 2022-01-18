package com.survivalcoding.network_apps.feature_conference_app_1.ui.conferences.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.survivalcoding.network_apps.databinding.ConferenceListItemBinding
import com.survivalcoding.network_apps.feature_conference_app_1.domain.model.Conference

class ConferenceAdapter(private val onClick: (Conference) -> Unit): ListAdapter<Conference, ConferenceViewHolder>(ConferenceDiffItemCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConferenceViewHolder {
        return ConferenceViewHolder(ConferenceListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ConferenceViewHolder, position: Int) {
        val conference = getItem(position)
        holder.itemView.setOnClickListener { onClick(conference) }
        return holder.bind(conference)
    }
}