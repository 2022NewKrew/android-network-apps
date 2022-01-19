package com.example.presentation.conferences.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.domain.model.Conference

class ConferenceDiffUtilItemCallback : DiffUtil.ItemCallback<Conference>() {
    override fun areItemsTheSame(oldItem: Conference, newItem: Conference) =
        oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: Conference, newItem: Conference) = oldItem == newItem
}