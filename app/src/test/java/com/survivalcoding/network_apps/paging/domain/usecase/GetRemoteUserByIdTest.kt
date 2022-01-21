package com.survivalcoding.network_apps.paging.domain.usecase

import com.survivalcoding.network_apps.paging.data.datasource.remote.PostRemoteDataSource
import com.survivalcoding.network_apps.paging.data.datasource.remote.RetrofitClient
import com.survivalcoding.network_apps.paging.data.repository.PostRepositoryImpl
import com.survivalcoding.network_apps.paging.domain.repository.PostRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetRemoteUserByIdTest {
    private lateinit var useCase: GetRemoteUserById
    private lateinit var repository: PostRepository


    @Before
    fun setUp() {
        repository =
            PostRepositoryImpl(postRemoteDataSource = PostRemoteDataSource(RetrofitClient.apiService))
        useCase = GetRemoteUserById(repository)
    }

    @Test
    fun getPostTest() = runBlocking {
        val result = useCase.invoke(1)
        assertEquals("Leanne Graham", result?.name)

        val result2 = useCase.invoke(11)
        assertEquals(null, result2)
    }
}