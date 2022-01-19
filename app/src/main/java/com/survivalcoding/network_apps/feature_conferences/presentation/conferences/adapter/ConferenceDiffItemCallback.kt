package com.survivalcoding.network_apps.feature_conferences.presentation.conferences.adapter

import androidx.recyclerview.widget.DiffUtil
import com.survivalcoding.network_apps.feature_conferences.domain.model.Conference

object ConferenceDiffItemCallback : DiffUtil.ItemCallback<Conference>() {
    override fun areItemsTheSame(oldItem: Conference, newItem: Conference): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Conference, newItem: Conference): Boolean {
        return oldItem == newItem
    }
}