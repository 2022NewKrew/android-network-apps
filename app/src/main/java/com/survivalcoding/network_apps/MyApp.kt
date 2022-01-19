package com.survivalcoding.network_apps

import android.app.Application
import com.survivalcoding.network_apps.feature_conference.data.datasource.network.RemoteCnfDataSource
import com.survivalcoding.network_apps.feature_conference.data.datasource.network.service.ConferenceService
import com.survivalcoding.network_apps.feature_conference.domain.repository.ConferenceRepository
import com.survivalcoding.network_apps.feature_basic.data.datasource.network.RemoteDataSource
import com.survivalcoding.network_apps.feature_basic.data.datasource.network.service.SampleJsonService
import com.survivalcoding.network_apps.feature_basic.data.repository.RemoteTodoRepositoryImpl
import com.survivalcoding.network_apps.feature_basic.domain.repository.TodoRepository
import com.survivalcoding.network_apps.feature_conference.data.repository.ConferenceRepositoryImpl
import com.survivalcoding.network_apps.feature_paging.data.datasource.network.PostDataSource
import com.survivalcoding.network_apps.feature_paging.data.datasource.network.UserDataSource
import com.survivalcoding.network_apps.feature_paging.data.datasource.network.service.JsonService
import com.survivalcoding.network_apps.feature_paging.data.repositoryimpl.PostRepositoryImpl
import com.survivalcoding.network_apps.feature_paging.data.repositoryimpl.UserRepositoryImpl
import com.survivalcoding.network_apps.feature_paging.domain.repository.PostRepository
import com.survivalcoding.network_apps.feature_paging.domain.repository.UserRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApp : Application() {


    private val jsonRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .build()
    }

    private val conferenceRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://raw.githubusercontent.com/")
            .build()
    }

    val sampleRepository: TodoRepository by lazy {
        RemoteTodoRepositoryImpl(
            RemoteDataSource(
                jsonRetrofit.create(SampleJsonService::class.java)
            )
        )
    }
    val conferenceRepository: ConferenceRepository by lazy {
        ConferenceRepositoryImpl(
            RemoteCnfDataSource(
                conferenceRetrofit.create(ConferenceService::class.java)
            )
        )
    }

    val postRepository: PostRepository by lazy {
        PostRepositoryImpl(
            PostDataSource(
                jsonRetrofit.create(JsonService::class.java)
            )
        )
    }

    val userRepository: UserRepository by lazy {
        UserRepositoryImpl(
            UserDataSource(
                jsonRetrofit.create(JsonService::class.java)
            )
        )
    }


}