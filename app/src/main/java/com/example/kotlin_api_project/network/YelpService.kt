package com.example.kotlin_api_project.network

import com.example.kotlin_api_project.model.Businesses
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

}
