package com.survivalcoding.network_apps

import android.app.Application
import com.survivalcoding.network_apps.feature_basic.data.datasource.network.RemoteDataSource
import com.survivalcoding.network_apps.feature_basic.data.datasource.network.service.SampleJsonService
import com.survivalcoding.network_apps.feature_basic.data.repository.RemoteTodoRepositoryImpl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApp : Application() {


    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .build()
    }

    val remoteTodoRepository: RemoteTodoRepositoryImpl by lazy {
        RemoteTodoRepositoryImpl(
            RemoteDataSource(
                retrofit.create(SampleJsonService::class.java)
            )
        )
    }

}