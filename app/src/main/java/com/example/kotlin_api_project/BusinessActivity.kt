package com.example.kotlin_api_project

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_api_project.adapter.BusinessAdapter
import com.example.kotlin_api_project.databinding.ActivityBusinessBinding
import com.example.kotlin_api_project.viewmodel.BusinessesViewModel
import com.example.kotlin_api_project.network.LocationManager
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

        businessesViewModel = ViewModelProvider(this)[BusinessesViewModel::class.java]

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

        // Access views using binding
        val bottomNavigationView = binding.bottomNavigationView
        bottomNavigationView.selectedItemId = R.id.bottom_businesses

        // Set a listener for item selection for bottom navigation
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
                    startActivity(Intent(applicationContext, CategoriesActivity::class.java))
                    true
                }
                else -> false
            }
        }

        //onclick of search button, search filled text-field
        searchBtn.setOnClickListener {
            val location = searchField.text.toString()
            businessesViewModel.fetchBusinesses(ApiRepository.apiKey, location)

            // Hide the keyboard
            hideKeyboard()
            searchField.clearFocus()

        }
    }

    @SuppressLint("NotifyDataSetChanged")
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

    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        val view = currentFocus ?: View(this)
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

}
