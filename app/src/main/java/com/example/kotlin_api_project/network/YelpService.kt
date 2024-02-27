package com.example.kotlin_api_project.network

import com.example.kotlin_api_project.model.Businesses
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface YelpService {
    @GET("businesses/search")
    suspend fun getBusinesses(
        @Header("Authorization") apiKey: String,
        @Query("term") term: String?,
        @Query("latitude") latitude: String,
        @Query("longitude") longitude: String
    ): Response<List<Businesses>>
}
