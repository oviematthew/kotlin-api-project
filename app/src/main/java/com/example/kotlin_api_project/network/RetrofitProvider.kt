package com.example.kotlin_api_project.network
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitProvider {
    private const val BASE_URL = "https://api.yelp.com/v3/"
    val retrofitInstance by lazy { getRetrofit() }
    fun createYelpService(): YelpService {
        return getRetrofit().create(YelpService::class.java)
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

