package com.survivalcoding.network_apps.conference.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.survivalcoding.network_apps.conference.domain.model.ConferenceInfo

/*
Note that DiffUtil, ListAdapter, and AsyncListDiffer require the list to not mutate while in use.
 This generally means that both the lists themselves and their elements
 (or at least, the properties of elements used in diffing) should not be modified directly.
 Instead, new lists should be provided any time content changes.
 It's common for lists passed to DiffUtil to share elements that have not mutated,
 so it is not strictly required to reload all data to use DiffUtil.
* */
object ConferenceDiffItemCallback : DiffUtil.ItemCallback<ConferenceInfo>() {
    override fun areItemsTheSame(oldItem: ConferenceInfo, newItem: ConferenceInfo): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: ConferenceInfo, newItem: ConferenceInfo): Boolean {
        return oldItem == newItem
    }
}