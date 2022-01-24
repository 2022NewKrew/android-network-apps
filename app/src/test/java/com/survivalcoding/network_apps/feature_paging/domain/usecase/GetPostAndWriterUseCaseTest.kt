package com.survivalcoding.network_apps.feature_paging.domain.usecase

import com.survivalcoding.network_apps.feature_paging.domain.model.Post
import com.survivalcoding.network_apps.feature_paging.domain.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetPostAndWriterUseCaseTest {

    private lateinit var getPostUseCase: GetPostsUseCase
    private lateinit var getUserUseCase: GetUserUseCase
    private lateinit var getPostAndWriterUseCase: GetPostAndWriterUseCase

    private val testPosts = listOf(
        Post(
            userId = 1,
            id = 1,
            title = "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
            body = "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
        ),
        Post(
            userId = 2,
            id = 11,
            title = "et ea vero quia laudantium autem",
            body = "delectus reiciendis molestiae occaecati non minima eveniet qui voluptatibus\naccusamus in eum beatae sit\nvel qui neque voluptates ut commodi qui incidunt\nut animi commodi"
        ),
        Post(
            userId = 3,
            id = 22,
            title = "dolor sint quo a velit explicabo quia nam",
            body = "eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita earum mollitia molestiae aut atque rem suscipit\nnam impedit esse"
        )
    )
    private val testUsers = listOf(
        User(
            id = 1,
            name = "Leanne Graham",
            username = "Bret",
        ),
        User(
            id = 2,
            name = "Ervin Howell",
            username = "Antonette",
        ),
        User(
            id = 3,
            name = "Clementine Bauch",
            username = "Samantha",
        )
    )

    @Before
    fun setUp() {
        getPostUseCase = Mockito.mock(GetPostsUseCase::class.java)
        runBlocking {
            Mockito.`when`(getPostUseCase.invoke(TEST_PAGE_INDEX, TEST_PAGE_SIZE))
                .thenReturn(testPosts)
        }

        getUserUseCase = Mockito.mock(GetUserUseCase::class.java)
        runBlocking {
            Mockito.`when`(getUserUseCase.invoke(1)).thenReturn(testUsers[0])
            Mockito.`when`(getUserUseCase.invoke(2)).thenReturn(testUsers[1])
            Mockito.`when`(getUserUseCase.invoke(3)).thenReturn(testUsers[2])
        }

        getPostAndWriterUseCase = GetPostAndWriterUseCase(getPostUseCase, getUserUseCase)
    }

    @Test
    fun `포스트의_작성자를_제대로_가져오는지_테스트`() {
        runBlocking {
            val postAndWriter = getPostAndWriterUseCase(
                TEST_PAGE_INDEX,
                TEST_PAGE_SIZE,
                CoroutineScope(Dispatchers.Unconfined)
            )

            val sortedPostAndWriter = postAndWriter.sortedBy { it.first.userId }
            val testPostAndWriters = testPosts.zip(testUsers)

            assertEquals(testPostAndWriters, sortedPostAndWriter)
        }
    }

    @After
    fun tearDown() {
    }

    companion object {
        private const val TEST_PAGE_INDEX = 0
        private const val TEST_PAGE_SIZE = 3
    }
}