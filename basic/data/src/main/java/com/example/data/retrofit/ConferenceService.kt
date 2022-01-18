package com.example.data.retrofit

import com.example.data.model.MoshiConference
import retrofit2.Response
import retrofit2.http.GET

interface ConferenceService {
    @GET("junsuk5/mock_json/main/conferences.json")
    suspend fun getConferences(): Response<List<MoshiConference>>
}