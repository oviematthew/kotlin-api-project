package com.example.kotlin_api_project.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin_api_project.model.Businesses
import com.example.kotlin_api_project.network.RetrofitProvider
import com.example.kotlin_api_project.repository.ApiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class BusinessesViewModel : ViewModel() {

    private val _businessesLiveData = MutableLiveData<List<Businesses>>()
    val businessesLiveData: MutableLiveData<List<Businesses>> get() = _businessesLiveData

    fun fetchBusinesses(apiKey: String, term: String, latitude: Double, longitude: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = ApiRepository(RetrofitProvider.createYelpService())
//                .fetchBusinessesFromServer(apiKey, term, latitude, longitude)

//            _businessesLiveData.postValue(response.body())
        }
    }
}
