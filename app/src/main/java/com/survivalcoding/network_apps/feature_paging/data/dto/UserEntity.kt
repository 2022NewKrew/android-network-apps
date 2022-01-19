package com.survivalcoding.network_apps.feature_paging.data.dto

import com.survivalcoding.network_apps.feature_paging.domain.model.Address
import com.survivalcoding.network_apps.feature_paging.domain.model.Company


data class UserEntity(
    val id: String?,
    val name: String?,
    val username: String?,
    val email: String?,
    val address: List<Address>?,
    val phone: String?,
    val website: String?,
    val company: List<Company>?
)