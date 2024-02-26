package com.example.kotlin_api_project.repository

import com.example.kotlin_api_project.model.Event
import com.example.kotlin_api_project.network.ApiService

class ApiRepository {

    suspend fun getNearbyEvents(apiService: ApiService, location: String?, latitude: Double?, longitude: Double?): List<Event>? {
        val response = apiService.getNearbyEvents(location ?: "", latitude, longitude)
        return response
    }
}
