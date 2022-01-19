package com.survivalcoding.network_apps.feature_basic.data.datasource

import com.survivalcoding.network_apps.feature_basic.domain.model.Todo

interface DataSource {
    suspend fun getTodo(id: Int): Todo
}