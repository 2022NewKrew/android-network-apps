package com.survivalcoding.network_apps.feature_paging.data.datasource.network

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.survivalcoding.network_apps.feature_paging.data.datasource.network.service.JsonService
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@RunWith(AndroidJUnit4::class)
class UserDataSourceTest {
    private lateinit var testRetrofit: Retrofit
    private lateinit var userDataSource: UserDataSource

    @Before
    fun setUp() {
        testRetrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://jsonplaceholder.typicode.com")
            .build()

        userDataSource = UserDataSource(
            testRetrofit.create(JsonService::class.java)
        )
    }

    @Test
    fun getUser() = runBlocking {
        assertEquals(
            1,
            userDataSource.getUser(1)!!.id
        )

        assertEquals(
            2,
            userDataSource.getUser(2)!!.id
        )
    }
}