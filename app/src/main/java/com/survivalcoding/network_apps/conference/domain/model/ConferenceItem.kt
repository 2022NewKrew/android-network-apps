package com.survivalcoding.network_apps.conference.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ConferenceItem(
    val name: String,
    val link: String,
    val start: String,
    val end: String,
    val location: String,
    @SerializedName("cocoa-only")
    val cocoaOnly: Boolean = false
) : Parcelable
