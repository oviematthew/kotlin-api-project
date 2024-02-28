package com.example.kotlin_api_project

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_api_project.adapter.EventsAdapter
import com.example.kotlin_api_project.databinding.ActivityEventsBinding
import com.example.kotlin_api_project.network.LocationManager
import com.example.kotlin_api_project.network.YelpService
import com.example.kotlin_api_project.network.RetrofitProvider
import com.example.kotlin_api_project.repository.ApiRepository
import com.example.kotlin_api_project.viewmodel.EventViewModel

class EventsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEventsBinding
    private lateinit var eventViewModel: EventViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the activity and set the contentView to the root of the xml
        binding = ActivityEventsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        eventViewModel = ViewModelProvider(this).get(EventViewModel::class.java)

        val adapter = EventsAdapter(emptyList())
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        updateEventList()

        val searchField = binding.searchEditText
        val locationBtn = binding.locationBtn

        //set a click listener to location button
        locationBtn.setOnClickListener {
            // Retrieve latitude and longitude from LocationViewModel
            val currentLocation = LocationManager.getCurrentLocation()
            if (currentLocation != null) {
                val latitude = currentLocation.latitude
                val longitude = currentLocation.longitude
                val longAndLat = "$latitude, $longitude"
                searchField.setText(longAndLat)
                eventViewModel.searchNearbyEvents(null, latitude, longitude)
            } else {
                Toast.makeText(this, "Current location is not available", Toast.LENGTH_SHORT).show()
            }

        }

        // Access views using binding
        val bottomNavigationView = binding.bottomNavigationView
        bottomNavigationView.selectedItemId = R.id.bottom_events

        // Set a listener for item selection
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.bottom_about -> {
                    startActivity(Intent(applicationContext, AboutActivity::class.java))
                    true
                }
                R.id.bottom_businesses -> {
                    startActivity(Intent(applicationContext, BusinessActivity::class.java))
                    true
                }
                R.id.bottom_events -> {
                    // Fetch events
                    updateEventList()
                    true
                }
                R.id.bottom_reviews -> {
                    startActivity(Intent(applicationContext, CategoriesActivity::class.java))
                    true
                }
                else -> false
            }
        }



        binding.searchButton.setOnClickListener {
//            val location = binding.searchEditText.text.toString()
            val location = searchField.text.toString()
            eventViewModel.searchNearbyEvents(location, null, null)

            // Hide the keyboard
            hideKeyboard()
            searchField.clearFocus()

        }
    }

    private fun updateEventList() {
        val apiService = RetrofitProvider.retrofitInstance.create(YelpService::class.java)
        val apiRepository = ApiRepository(apiService)

        val currentLocation = LocationManager.getCurrentLocation()
        if (currentLocation != null) {
            val latitude = currentLocation.latitude
            val longitude = currentLocation.longitude
            eventViewModel.searchNearbyEvents(null, latitude, longitude)
        } else {
            Toast.makeText(this, "Current location is not available", Toast.LENGTH_SHORT).show()
        }

        eventViewModel.eventsData.observe(this, Observer { events ->
            if (events != null) {
                // Update UI
                val adapter = binding.recyclerView.adapter as EventsAdapter
                adapter.events = events
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
