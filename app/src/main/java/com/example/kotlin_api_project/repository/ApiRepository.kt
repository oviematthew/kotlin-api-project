package com.example.kotlin_api_project.repository

import android.util.Log
import com.example.kotlin_api_project.network.YelpService
import com.example.kotlin_api_project.model.Businesses
import com.example.kotlin_api_project.model.Event
import com.example.kotlin_api_project.network.RetrofitProvider
import retrofit2.Response

class ApiRepository(private val yelpService: YelpService) {

//    suspend fun fetchBusinessesFromServer(
//        term: String,
//        latitude: Double,
//        longitude: Double
//    ): Response<List<Businesses>> {
//        return yelpService.getBusinesses(apiKey, term, latitude.toString(), longitude.toString())
//    }

    suspend fun getBusinesses(apiKey: String, location: String): List<Businesses>? {
        return try {
            val response = yelpService.getBusinesses("Bearer $apiKey", location)
            logApiResponse(response)

            if (response.isSuccessful) {
                val businessesResponse = response.body()
                businessesResponse?.businesses // Return the list of businesses from the BusinessesResponse
            } else {
                null
            }
        } catch (e: Exception) {
            Log.e("ApiRepository", "Error fetching businesses", e)
            null
        }
    }



    suspend fun getNearbyEvents(location: String?, latitude: Double?, longitude: Double?): List<Event>? {
        return try {
            val response = yelpService.getNearbyEvents("Bearer ${apiKey}", location, latitude, longitude)
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



    companion object {
        const val apiKey = "HwsTLUWs7iiSpkg97eEZIYe-GEghXhhisTp6m7-446Pp_6xi16Kbt_U5pIp1hJgbEHp6DmJTlytzXBk22xQlbXE-a8tnJX2h1KfM-ay1ewXCa2i5HHcKd88Oaa_aZXYx"
    }
}
