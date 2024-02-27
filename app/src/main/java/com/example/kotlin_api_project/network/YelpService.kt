package com.example.kotlin_api_project.network

import com.example.kotlin_api_project.model.Businesses
import com.example.kotlin_api_project.model.EventsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface YelpService {
    @GET("businesses/search?term={term}&location={location}&sort_by=best_match&limit=20")
    suspend fun getBusinesses(
        @Path("term") term: String,
        @Path("location") location: String,
        @Header("accept") acceptHeader: String = "application/json",
        @Header("Authorization") authorizationHeader: String
    ): Response<List<Businesses>>

    @GET("events?limit=3&sort_by=desc&sort_on=popularity")
    suspend fun getNearbyEvents(
        @Header("Authorization") authorization: String,
        @Query("location") location: String? = null,
        @Query("latitude") latitude: Double? = null,
        @Query("longitude") longitude: Double? = null
    ): Response<EventsResponse>

}
