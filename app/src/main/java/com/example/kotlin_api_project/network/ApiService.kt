package com.example.kotlin_api_project.network

import com.example.kotlin_api_project.model.Event
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("events")
    suspend fun getNearbyEvents(
        @Query("location") location: String,
        @Query("latitude") latitude: Double?,
        @Query("longitude") longitude: Double?
    ): List<Event>
}
