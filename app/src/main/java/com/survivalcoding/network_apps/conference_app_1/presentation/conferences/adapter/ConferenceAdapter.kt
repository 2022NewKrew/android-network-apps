package com.survivalcoding.network_apps.conference_app_1.presentation.conferences.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.survivalcoding.network_apps.R
import com.survivalcoding.network_apps.conference_app_1.domain.model.Conference

class ConferenceAdapter(private val onClickView: (Conference) -> Unit) :
    ListAdapter<Conference, ConferenceViewHolder>(ConferenceDiffItemCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConferenceViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_conference, parent, false)
        return ConferenceViewHolder(view, onClickView)
    }

    override fun onBindViewHolder(holder: ConferenceViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}