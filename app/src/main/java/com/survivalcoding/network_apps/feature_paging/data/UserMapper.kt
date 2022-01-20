package com.survivalcoding.network_apps.feature_paging.data

import com.survivalcoding.network_apps.feature_paging.data.dto.UserEntity
import com.survivalcoding.network_apps.feature_paging.domain.model.Address
import com.survivalcoding.network_apps.feature_paging.domain.model.Company
import com.survivalcoding.network_apps.feature_paging.domain.model.User

object UserMapper {
    fun toModel(user: UserEntity) = User(id = user.id, name = user.name, username = user.username)

    fun toEntity(
        user: User,
        email: String? = null,
        address: Address? = null,
        phone: String? = null,
        website: String? = null,
        company: Company? = null
    ) = UserEntity(
        id = user.id, name = user.name, username = user.username,
        email = email,
        address = address,
        phone = phone,
        website = website,
        company = company
    )
}