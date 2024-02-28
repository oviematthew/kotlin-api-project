package com.example.kotlin_api_project.network

import com.google.android.gms.maps.model.LatLng

object LocationManager {
    private var currentLocation: LatLng? = null

    fun getCurrentLocation(): LatLng? {
        return currentLocation
    }

    fun setCurrentLocation(latitude: Double, longitude: Double) {
        currentLocation = LatLng(latitude, longitude)
    }
}
