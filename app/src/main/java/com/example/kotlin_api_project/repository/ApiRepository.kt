package com.example.kotlin_api_project.repository

import com.example.kotlin_api_project.network.YelpService
import com.example.kotlin_api_project.model.Businesses
import retrofit2.Response

class ApiRepository(private val yelpService: YelpService) {

    suspend fun fetchBusinessesFromServer(
        term: String?,
        latitude: Double,
        longitude: Double
    ): Response<List<Businesses>> {
        return yelpService.getBusinesses(apiKey, term, latitude.toString(), longitude.toString())
    }

    companion object {
        private const val apiKey = "HwsTLUWs7iiSpkg97eEZIYe-GEghXhhisTp6m7-446Pp_6xi16Kbt_U5pIp1hJgbEHp6DmJTlytzXBk22xQlbXE-a8tnJX2h1KfM-ay1ewXCa2i5HHcKd88Oaa_aZXYx"
    }
}
