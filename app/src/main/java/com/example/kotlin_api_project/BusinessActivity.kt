package com.example.kotlin_api_project

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_api_project.adapter.BusinessAdapter
import com.example.kotlin_api_project.databinding.ActivityBusinessBinding
import com.example.kotlin_api_project.viewmodel.BusinessesViewModel
import com.example.kotlin_api_project.model.LocationManager
import com.example.kotlin_api_project.repository.ApiRepository

class BusinessActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBusinessBinding
    private lateinit var businessesViewModel: BusinessesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBusinessBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = BusinessAdapter(emptyList())
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        businessesViewModel = ViewModelProvider(this).get(BusinessesViewModel::class.java)

        updateBusinessList()


        val locationBtn = binding.locationBtn
        val searchField = binding.searchEditText
        val searchBtn = binding.searchButton

        //set a click listener to location button
        locationBtn.setOnClickListener {
            // Retrieve latitude and longitude from LocationViewModel
            val currentLocation = LocationManager.getCurrentLocation()
            if (currentLocation != null) {
                val latitude = currentLocation.latitude
                val longitude = currentLocation.longitude
                val longAndLat = "$latitude, $longitude"
                searchField.setText(longAndLat)
                businessesViewModel.fetchBusinesses(ApiRepository.apiKey, longAndLat)
            } else {
                Toast.makeText(this, "Current location is not available", Toast.LENGTH_SHORT).show()
            }

        }

        val bottomNavigationView = binding.bottomNavigationView
        bottomNavigationView.selectedItemId = R.id.bottom_businesses

        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.bottom_about -> {
                    startActivity(Intent(applicationContext, AboutActivity::class.java))
                    true
                }
                R.id.bottom_businesses -> {
                    updateBusinessList()
                    true
                }
                R.id.bottom_events -> {
                    startActivity(Intent(applicationContext, EventsActivity::class.java))
                    true
                }
                R.id.bottom_reviews -> {
                    startActivity(Intent(applicationContext, ReviewsActivity::class.java))
                    true
                }
                else -> false
            }
        }

        searchBtn.setOnClickListener {
            val location = searchField.text.toString()
            businessesViewModel.fetchBusinesses(
                "HwsTLUWs7iiSpkg97eEZIYe-GEghXhhisTp6m7-446Pp_6xi16Kbt_U5pIp1hJgbEHp6DmJTlytzXBk22xQlbXE-a8tnJX2h1KfM-ay1ewXCa2i5HHcKd88Oaa_aZXYx",
                location
            )
        }
    }



    private fun updateBusinessList() {
        val currentLocation = LocationManager.getCurrentLocation()
        if (currentLocation != null) {
            val latitude = currentLocation.latitude
            val longitude = currentLocation.longitude
            val longAndLat = "$latitude, $longitude"
            businessesViewModel.fetchBusinesses(ApiRepository.apiKey, longAndLat)
        } else {
            Toast.makeText(this, "Current location is not available", Toast.LENGTH_SHORT).show()
        }

        businessesViewModel.businessesLiveData.observe(this, Observer { businesses ->
            if (businesses != null) {
                val adapter = binding.recyclerView.adapter as BusinessAdapter
                adapter.businessList = businesses
                adapter.notifyDataSetChanged()
            }
        })

    }
}
