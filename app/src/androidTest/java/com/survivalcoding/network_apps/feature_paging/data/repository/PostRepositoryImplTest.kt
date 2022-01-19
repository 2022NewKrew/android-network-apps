package com.survivalcoding.network_apps.feature_paging.data.repository

import android.util.Log
import com.survivalcoding.network_apps.feature_paging.domain.repository.PostRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
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
            val postItems = postRepositoryImpl.getPosts(1)
            Log.d(TAG, "getPosts: $postItems")
            assertNotEquals(0, postItems.size)
        }
    }
}