package com.survivalcoding.network_apps.conference.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.survivalcoding.network_apps.conference.domain.model.ConferenceInfo

class ConferenceListAdapter(
    private val onClicked: (ConferenceInfo) -> Unit
) :
    ListAdapter<ConferenceInfo, ConferenceViewHolder>(ConferenceDiffItemCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConferenceViewHolder =
        ConferenceViewHolder.builder(parent)

    override fun onBindViewHolder(holder: ConferenceViewHolder, position: Int) {
        holder.bind(getItem(position), onClicked)
    }
}