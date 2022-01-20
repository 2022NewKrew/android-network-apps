package com.example.data.datasource

import com.example.data.retrofit.ConferenceService
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection
import java.nio.charset.StandardCharsets

class ConferenceRetrofitDataSourceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var testService: ConferenceService

    @Before
    fun mockServer() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        testService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ConferenceService::class.java)
    }

    private fun enqueueResponse(fileName: String) {
        val inputStream = javaClass.classLoader!!.getResourceAsStream("api-response/$fileName")
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(source.readString(StandardCharsets.UTF_8))
        mockWebServer.enqueue(mockResponse)
    }

    @After
    fun stopServer() {
        mockWebServer.shutdown()
    }

    @Test
    fun getConferences() {
        enqueueResponse("/ConferencesResponse.json")
        runBlocking {
            val response = testService.getConferences()
            val responseBody = requireNotNull(response.body())
            mockWebServer.takeRequest()

            assertEquals(8, responseBody.size)
            assertEquals(responseBody[0].name, "SwiftLeeds")
            assertEquals(responseBody[7].name, "MobileOptimized 2019")
        }
    }
}