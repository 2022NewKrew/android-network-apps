package com.survivalcoding.network_apps.conference.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.network_apps.conference.domain.model.ConferenceInfo
import com.survivalcoding.network_apps.databinding.ItemConferenceBinding

class ConferenceViewHolder(private val binding: ItemConferenceBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ConferenceInfo) {
        binding.name.text = item.name
        binding.location.text = item.location
    }

    companion object {
        fun builder(parent: ViewGroup): ConferenceViewHolder = ConferenceViewHolder(
            ItemConferenceBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}