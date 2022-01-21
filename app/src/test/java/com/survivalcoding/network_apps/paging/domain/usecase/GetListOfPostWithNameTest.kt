package com.survivalcoding.network_apps.paging.domain.usecase

import com.survivalcoding.network_apps.paging.data.datasource.remote.RetrofitClient
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetListOfPostWithNameTest {
    private lateinit var userUseCase: GetRemoteUserById
    private lateinit var postUseCase: GetRemotePosts
    private lateinit var postWithNameUseCase: GetListOfPostWithName


    @Before
    fun setUp() {
        userUseCase = GetRemoteUserById(RetrofitClient.apiService)
        postUseCase = GetRemotePosts(RetrofitClient.apiService)
        postWithNameUseCase = GetListOfPostWithName(postUseCase, userUseCase)
    }

    @Test
    fun getPostWithNameTest() = runBlocking {
        val result = postWithNameUseCase.invoke(1, 20)
        assertEquals(20, result.size)
        assertEquals(result[0].user, "Leanne Graham")

        val result2 = postWithNameUseCase.invoke(6, 20)
        assertEquals(0, result2.size)
    }
}