package com.survivalcoding.network_apps.feature_basic.data.datasource.network

import com.survivalcoding.network_apps.feature_basic.data.datasource.DataSource
import com.survivalcoding.network_apps.feature_basic.data.datasource.network.service.SampleJsonService

class RemoteDataSource(private val service: SampleJsonService) : DataSource {
    override suspend fun getTodo(id: Int) = service.getTodo(id)
}