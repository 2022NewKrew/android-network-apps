package com.survivalcoding.network_apps.conferences.presentation.list.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.survivalcoding.network_apps.conferences.domain.model.Conference

class ConferenceAdapter(
    private val clickEvent: (conference: Conference) -> Unit,
) : ListAdapter<Conference, ConferenceHolder>(DiffUtilCallBack) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConferenceHolder {
        return ConferenceHolder(parent)
    }

    override fun onBindViewHolder(holder: ConferenceHolder, position: Int) {
        holder.binding(getItem(position), clickEvent)
    }
}