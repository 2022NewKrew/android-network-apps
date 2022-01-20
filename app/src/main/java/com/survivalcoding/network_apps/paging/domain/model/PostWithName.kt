package com.survivalcoding.network_apps.paging.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostWithName(
    val body: String,
    val id: Int,
    val title: String,
    val user: String
) : Parcelable