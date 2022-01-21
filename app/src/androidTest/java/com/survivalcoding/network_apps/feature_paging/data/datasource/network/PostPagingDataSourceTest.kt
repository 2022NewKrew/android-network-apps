package com.survivalcoding.network_apps.feature_paging.data.datasource.network

import androidx.paging.PagingSource
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.survivalcoding.network_apps.feature_paging.data.datasource.network.service.JsonService
import com.survivalcoding.network_apps.feature_paging.data.repositoryimpl.PostRepositoryImpl
import com.survivalcoding.network_apps.feature_paging.data.repositoryimpl.UserRepositoryImpl
import com.survivalcoding.network_apps.feature_paging.domain.model.Post
import com.survivalcoding.network_apps.feature_paging.domain.repository.PostRepository
import com.survivalcoding.network_apps.feature_paging.domain.repository.UserRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@RunWith(AndroidJUnit4::class)
class PostPagingDataSourceTest {

    private lateinit var testRetrofit: Retrofit
    private lateinit var testDataSource: PostPagingDataSource
    private val mockPosts = listOf(
        Post(
            1,
            1,
            "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
            "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
        ),
        Post(
            1,
            2,
            "qui est esse",
            "est rerum tempore vitae\nsequi sint nihil reprehenderit dolor beatae ea dolores neque\nfugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis\nqui aperiam non debitis possimus qui neque nisi nulla"
        ),
    )


    @Before
    fun setUp() {
        testRetrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://jsonplaceholder.typicode.com")
            .build()
        testDataSource = PostPagingDataSource(testRetrofit.create(JsonService::class.java), 2)


    }

    @Test
    fun load() = runBlocking {
        assertEquals(
            PagingSource.LoadResult.Page(
                data = listOf(mockPosts[0], mockPosts[1]),
                prevKey = null,
                nextKey = 2
            ),
            testDataSource.load(
                PagingSource.LoadParams.Refresh(
                    key = null,
                    loadSize = 2,
                    placeholdersEnabled = false
                )
            ),
        )
    }

    @Test
    fun getRefreshKey() {
        //todo
    }
}