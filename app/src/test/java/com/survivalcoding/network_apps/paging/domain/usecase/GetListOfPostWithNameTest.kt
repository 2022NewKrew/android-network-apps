package com.survivalcoding.network_apps.paging.domain.usecase

import com.survivalcoding.network_apps.paging.data.datasource.remote.PostRemoteDataSource
import com.survivalcoding.network_apps.paging.data.datasource.remote.RetrofitClient
import com.survivalcoding.network_apps.paging.data.repository.PostRepositoryImpl
import com.survivalcoding.network_apps.paging.domain.model.Post
import com.survivalcoding.network_apps.paging.domain.repository.PostRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetListOfPostWithNameTest {
    private lateinit var userUseCase: GetRemoteUserById
    private lateinit var postWithNameUseCase: GetListOfPostWithName
    private lateinit var repository: PostRepository


    @Before
    fun setUp() {
        repository =
            PostRepositoryImpl(postRemoteDataSource = PostRemoteDataSource(RetrofitClient.apiService))
        userUseCase = GetRemoteUserById(repository)
        postWithNameUseCase = GetListOfPostWithName(userUseCase)
    }

    @Test
    fun getPostWithNameTest() = runBlocking {
        val mockUserMap = mutableMapOf<Int, String>()

        val mockPost1 = Post(body = "1", id = 1, title = "1", userId = 1)
        val result = postWithNameUseCase.invoke(mockUserMap, mockPost1)
        assertEquals("Leanne Graham", result.user)
        assertEquals(1, mockUserMap.size)

        val mockPost2 = Post(body = "2", id = 2, title = "2", userId = 3)
        val result2 = postWithNameUseCase.invoke(mockUserMap, mockPost2)
        assertEquals("Clementine Bauch", result2.user)
        assertEquals(2, mockUserMap.size)

        val mockPost3 = Post(body = "1", id = 3, title = "1", userId = 1)
        val result3 = postWithNameUseCase.invoke(mockUserMap, mockPost3)
        assertEquals("Leanne Graham", result3.user)
        assertEquals(2, mockUserMap.size)

        val mockNullPost = Post(body = "2", id = 4, title = "3", userId = 11)
        val resultNull = postWithNameUseCase.invoke(mockUserMap, mockNullPost)
        assertEquals(null, resultNull.user)
        assertEquals(2, mockUserMap.size)
    }
}