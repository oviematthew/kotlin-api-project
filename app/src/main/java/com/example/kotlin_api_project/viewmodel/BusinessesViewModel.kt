package com.example.kotlin_api_project.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin_api_project.model.Businesses
import com.example.kotlin_api_project.network.RetrofitProvider
import com.example.kotlin_api_project.network.YelpService
import com.example.kotlin_api_project.repository.ApiRepository
import kotlinx.coroutines.launch

class BusinessesViewModel : ViewModel() {

    private val repository: ApiRepository
    private val _businessesLiveData = MutableLiveData<List<Businesses>?>()
    val businessesLiveData: LiveData<List<Businesses>?> get() = _businessesLiveData

    init {
        val apiService = RetrofitProvider.retrofitInstance.create(YelpService::class.java)
        repository = ApiRepository(apiService)
    }

    fun fetchBusinesses(apiKey: String, location: String) {
        viewModelScope.launch {

                val response = repository.getBusinesses(apiKey, location)
                _businessesLiveData.value = response

        }
    }
}
