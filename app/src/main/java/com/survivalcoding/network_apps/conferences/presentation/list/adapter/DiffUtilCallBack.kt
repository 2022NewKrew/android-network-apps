package com.survivalcoding.network_apps.conferences.presentation.list.adapter

import androidx.recyclerview.widget.DiffUtil
import com.survivalcoding.network_apps.conferences.domain.model.Conference

object DiffUtilCallBack : DiffUtil.ItemCallback<Conference>() {
    override fun areItemsTheSame(oldItem: Conference, newItem: Conference): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Conference, newItem: Conference): Boolean {
        return oldItem == newItem
    }
}