package com.example.kotlin_api_project.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin_api_project.model.Businesses
import com.example.kotlin_api_project.network.RetrofitProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Response

class BusinessesViewModel : ViewModel() {

    private val _businessesList = MutableLiveData<List<Businesses>>()
    val businessesList: LiveData<List<Businesses>> get() = _businessesList

    fun searchBusinesses(apiKey: String, term: String, latitude: Double, longitude: Double) {
        runBlocking {
            launch(Dispatchers.IO) {
                val response = RetrofitProvider.createYelpService()
                    .getBusinesses(apiKey, term, latitude.toString(), longitude.toString())

                _businessesList.postValue(response.body())


            }
        }

    }

}

