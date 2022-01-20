package com.survivalcoding.network_apps.paging.domain.usecase

import com.survivalcoding.network_apps.paging.data.datasource.remote.RetrofitClient
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetRemotePostsTest {
    private lateinit var useCase: GetRemotePosts

    @Before
    fun setUp() {
        useCase = GetRemotePosts(RetrofitClient.apiService)
    }

    @Test
    fun getPostTest() = runBlocking {
        val result = useCase.invoke(1, 20)
        assertEquals(20, result.size)


        val result2 = useCase.invoke(6, 20)
        assertEquals(0, result2.size)

        val result3 = useCase.invoke(5, 20)
        assertEquals("quaerat velit veniam amet cupiditate aut numquam ut sequi", result3[15].title)
    }


}