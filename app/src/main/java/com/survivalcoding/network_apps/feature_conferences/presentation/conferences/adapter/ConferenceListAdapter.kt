package com.survivalcoding.network_apps.feature_conferences.presentation.conferences.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.survivalcoding.network_apps.databinding.ItemConferenceBinding
import com.survivalcoding.network_apps.feature_conferences.domain.model.Conference

class ConferenceListAdapter(
    private val itemClickListener: (Conference) -> Unit
) : ListAdapter<Conference, ConferenceViewHolder>(ConferenceDiffItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConferenceViewHolder {
        return ConferenceViewHolder(
            ItemConferenceBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), itemClickListener
        )
    }

    override fun onBindViewHolder(holder: ConferenceViewHolder, position: Int) {
        return holder.bind(getItem(position))
    }
}