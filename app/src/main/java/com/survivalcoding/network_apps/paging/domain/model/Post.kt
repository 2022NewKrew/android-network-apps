package com.survivalcoding.network_apps.paging.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Post(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
) : Parcelable