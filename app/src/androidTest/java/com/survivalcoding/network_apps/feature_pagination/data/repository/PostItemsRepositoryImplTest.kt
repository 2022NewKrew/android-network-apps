package com.survivalcoding.network_apps.feature_pagination.data.repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.network_apps.feature_pagination.data.datasource.remote.PageRemotePostItemDataSource
import com.survivalcoding.network_apps.feature_pagination.data.datasource.remote.service.PageResourceService
import com.survivalcoding.network_apps.feature_pagination.domain.repository.PostItemRepository
import com.survivalcoding.network_apps.feature_pagination.domain.usecase.BaseUseCase
import com.survivalcoding.network_apps.feature_pagination.domain.usecase.GetPostItemsUseCase
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PostItemsRepositoryImplTest {
    private lateinit var retrofit: Retrofit
    private lateinit var repository: PostItemRepository
    private lateinit var useCase: GetPostItemsUseCase

    @Before
    fun setUp() {
        retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(PageResourceService::class.java)
        repository = PostItemsRepositoryImpl(PageRemotePostItemDataSource())
        useCase = GetPostItemsUseCase(repository)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getPostItems() = runBlocking {
        val result = useCase.invoke()
    }
}
