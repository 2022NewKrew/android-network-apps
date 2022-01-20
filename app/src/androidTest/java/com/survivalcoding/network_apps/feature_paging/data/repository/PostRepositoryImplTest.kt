package com.survivalcoding.network_apps.feature_paging.data.repository

import android.util.Log
import androidx.paging.flatMap
import androidx.paging.map
import com.survivalcoding.network_apps.feature_paging.domain.repository.PostRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class PostRepositoryImplTest {
    private val TAG = this::class.java.simpleName

    @get: Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var postRepositoryImpl: PostRepository

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun getPosts() {

        runBlocking {
            val postItems = postRepositoryImpl.getPosts()
            postItems.collect { pagingData ->
                pagingData.map {
                    Log.d(TAG, "post: $it")
                    assertNotEquals(-1, it.id)
                }
            }
        }
    }
}