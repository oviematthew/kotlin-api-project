package com.example.kotlin_api_project.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin_api_project.model.Category
import com.example.kotlin_api_project.model.CategoryResponse
import com.example.kotlin_api_project.network.RetrofitProvider
import com.example.kotlin_api_project.network.YelpService
import com.example.kotlin_api_project.repository.ApiRepository
import kotlinx.coroutines.launch

class CategoryViewModel : ViewModel() {

    private val repository: ApiRepository
    private val _categoryLiveData = MutableLiveData<List<Category>?>()
    val categoryLiveData: LiveData<List<Category>?> get() = _categoryLiveData

    init {
        val apiService = RetrofitProvider.retrofitInstance.create(YelpService::class.java)
        repository = ApiRepository(apiService)
    }

    fun fetchCategories(apiKey: String) {
        viewModelScope.launch {
            val response = repository.getCategories(apiKey)
            _categoryLiveData.value = response // Update to get the list of categories from the CategoryResponse
        }
    }
}
