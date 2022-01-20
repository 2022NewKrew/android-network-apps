package com.survivalcoding.network_apps.feature_pagination.data.repository

import androidx.paging.PagingSource
import com.survivalcoding.network_apps.feature_pagination.data.datasource.remote.PageRemotePostItemDataSource
import com.survivalcoding.network_apps.feature_pagination.data.datasource.remote.service.PageResourceService
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PostItemsRepositoryImplTest {
    private lateinit var retrofit: Retrofit
    private lateinit var pagingSource: PageRemotePostItemDataSource

    @Before
    fun setUp() {
        retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(PageResourceService::class.java)
        pagingSource = PageRemotePostItemDataSource(service)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getPostItems() {
        runBlocking {
            val result = pagingSource.load(
                PagingSource.LoadParams.Prepend(
                    1,
                    2,
                    false,
                )
            )

            when (result) {
                is PagingSource.LoadResult.Error -> assert(false)
                is PagingSource.LoadResult.Invalid -> assert(false)
                is PagingSource.LoadResult.Page -> {
//                    assert(result.data.size == 5)
                    assert(result.data[0].title == "sunt aut facere repellat provident occaecati excepturi optio reprehenderit")
                }
            }
        }
    }
}