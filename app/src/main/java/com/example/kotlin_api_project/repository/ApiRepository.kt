package com.example.kotlin_api_project.repository

import android.util.Log
import com.example.kotlin_api_project.model.Event
import com.example.kotlin_api_project.network.ApiService
import com.example.kotlin_api_project.network.RetrofitProvider
import retrofit2.Response

class ApiRepository(private val apiService: ApiService) {

    suspend fun getNearbyEvents(location: String?, latitude: Double?, longitude: Double?): List<Event>? {
        return try {
            val response = apiService.getNearbyEvents("Bearer ${RetrofitProvider.apiKey}", location, latitude, longitude)
            logApiResponse(response)

            if (response.isSuccessful) {
                val eventsResponse = response.body()
                eventsResponse?.events // Return the list of events from the EventsResponse
            } else {
                null
            }
        } catch (e: Exception) {
            Log.e("ApiRepository", "Error fetching nearby events", e)
            null
        }
    }

    private fun <T> logApiResponse(response: Response<T>) {
        Log.d("ApiRepository", "API Response: ${response.body()}")
    }
}

