package com.survivalcoding.network_apps.feature_conference.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

// 변경사항
@Parcelize
data class ConferenceInfo(
    val name: String,
    val link: String,
    val start: String,
    val end: String,
    val location: String
) : Parcelable