package com.example.kotlin_api_project.network

import com.example.kotlin_api_project.model.Businesses
import com.example.kotlin_api_project.model.BusinessesResponse
import com.example.kotlin_api_project.model.CategoryResponse
import com.example.kotlin_api_project.model.EventsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface YelpService {
    @GET("businesses/search")
    suspend fun getBusinesses(
        @Header("Authorization") authorization: String,
        @Query("location") location: String,
        @Query("categories") categories: String? = null,
        @Query("sort_by") sortBy: String = "best_match",
        @Query("limit") limit: Int = 50
    ): Response<BusinessesResponse>

    @GET("events?limit=3&sort_by=desc&sort_on=popularity")
    suspend fun getNearbyEvents(
        @Header("Authorization") authorization: String,
        @Query("location") location: String? = null,
        @Query("latitude") latitude: Double? = null,
        @Query("longitude") longitude: Double? = null
    ): Response<EventsResponse>

    @GET("categories")
    suspend fun getCategories(
        @Header("Authorization") authorization: String
    ): Response<CategoryResponse>
}
