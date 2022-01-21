package com.survivalcoding.network_apps.paging.domain.usecase

import com.survivalcoding.network_apps.paging.data.datasource.remote.RetrofitClient
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetRemoteUserByIdTest {
    private lateinit var useCase: GetRemoteUserById

    @Before
    fun setUp() {
        useCase = GetRemoteUserById(RetrofitClient.apiService)
    }

    @Test
    fun getPostTest() = runBlocking {
        val result = useCase.invoke(1)
        assertEquals("Leanne Graham", result?.name)

        val result2 = useCase.invoke(11)
        assertEquals(null, result2)
    }
}