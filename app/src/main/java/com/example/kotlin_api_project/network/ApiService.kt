package com.example.kotlin_api_project.network

import com.example.kotlin_api_project.model.Event
import com.example.kotlin_api_project.model.EventsResponse
import com.example.kotlin_api_project.network.RetrofitProvider
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {

    @GET("events?limit=3&sort_by=desc&sort_on=popularity")
    suspend fun getNearbyEvents(
        @Header("Authorization") authorization: String = "Bearer ${RetrofitProvider.apiKey}",
        @Query("location") location: String? = null,
        @Query("latitude") latitude: Double? = null,
        @Query("longitude") longitude: Double? = null
    ): Response<EventsResponse>
}

