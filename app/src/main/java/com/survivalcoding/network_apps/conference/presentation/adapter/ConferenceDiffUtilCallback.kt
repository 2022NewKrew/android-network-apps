package com.survivalcoding.network_apps.conference.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.survivalcoding.network_apps.conference.domain.model.ConferenceItem

object ConferenceDiffUtilCallback : DiffUtil.ItemCallback<ConferenceItem>() {
    override fun areItemsTheSame(oldItem: ConferenceItem, newItem: ConferenceItem) =
        oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: ConferenceItem, newItem: ConferenceItem) =
        oldItem == newItem
}