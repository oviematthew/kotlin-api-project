package com.example.kotlin_api_project.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LocationViewModel : ViewModel() {

    private val _locationData = MutableLiveData<Pair<Double, Double>>()
    val locationData: LiveData<Pair<Double, Double>> = _locationData

    fun setLocation(latitude: Double, longitude: Double) {
        _locationData.value = Pair(latitude, longitude)
    }
}
