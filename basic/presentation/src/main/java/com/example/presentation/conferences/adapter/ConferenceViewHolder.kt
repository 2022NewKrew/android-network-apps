package com.example.presentation.conferences.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.Conference
import com.example.presentation.databinding.ConferenceListItemBinding

class ConferenceViewHolder private constructor(private val binding: ConferenceListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(conference: Conference, onItemClick: (Conference) -> Unit) {
        binding.nameTextView.text = conference.name
        binding.locationTextView.text = conference.location
        binding.root.setOnClickListener { onItemClick(conference) }
    }

    companion object {
        fun from(parent: ViewGroup): ConferenceViewHolder {
            return ConferenceViewHolder(
                ConferenceListItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }
}