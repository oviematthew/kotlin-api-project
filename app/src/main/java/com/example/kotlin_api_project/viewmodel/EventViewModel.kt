package com.example.kotlin_api_project.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin_api_project.model.Event
import com.example.kotlin_api_project.network.RetrofitProvider
import com.example.kotlin_api_project.network.YelpService
import com.example.kotlin_api_project.repository.ApiRepository
import kotlinx.coroutines.launch

class EventViewModel : ViewModel() {

    private val repository: ApiRepository
    private val _eventsData = MutableLiveData<List<Event>?>()
    val eventsData: LiveData<List<Event>?> get() = _eventsData

    init {
        val apiService = RetrofitProvider.retrofitInstance.create(YelpService::class.java)
        repository = ApiRepository(apiService)
    }

    fun searchNearbyEvents(location: String?, latitude: Double?, longitude: Double?) {
        viewModelScope.launch {
            val eData = repository.getNearbyEvents(location, latitude, longitude)
            _eventsData.value = eData
        }
    }
}


