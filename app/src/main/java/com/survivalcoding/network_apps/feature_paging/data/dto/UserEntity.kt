package com.survivalcoding.network_apps.feature_paging.data.dto

import com.survivalcoding.network_apps.feature_paging.domain.model.Address
import com.survivalcoding.network_apps.feature_paging.domain.model.Company


data class UserEntity(
    val id: Int?,
    val name: String?,
    val username: String?,
    val email: String?,
    val address: Address?,
    val phone: String?,
    val website: String?,
    val company: Company?
)