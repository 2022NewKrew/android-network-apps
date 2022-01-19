package com.survivalcoding.network_apps.feature_paging.data.datasource

import android.util.Log
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class PostDataSourceTest {
    private val TAG = this::class.java.simpleName

    @get: Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var postService: PostService

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun getPosts() {
        runBlocking {
            val posts = postService.getPosts(1).body()
            Log.d(TAG, "getPosts: $posts")
            assertNotEquals(0, posts?.size ?: 0)
        }
    }

    @Test
    fun getUserById() {
        runBlocking {
            val user = postService.getUserById(1).body()
            Log.d(TAG, "getUserById: $user")
            assertNotEquals(null, user)
        }
    }
}