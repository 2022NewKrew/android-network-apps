package com.example.domain.model

import java.io.Serializable

data class Conference(
    val name: String? = null,
    val link: String? = null,
    val start: String? = null,
    val end: String? = null,
    val location: String? = null,
): Serializable