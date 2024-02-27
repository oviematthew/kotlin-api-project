package com.example.kotlin_api_project

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_api_project.databinding.ActivityEventsBinding
import com.example.kotlin_api_project.network.ApiService
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

        // Access views using binding
        val bottomNavigationView = binding.bottomNavigationView
        bottomNavigationView.selectedItemId = R.id.bottom_events

        // Set a listener for item selection
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.bottom_about -> {
                    startActivity(Intent(applicationContext, AboutActivity::class.java))
                    overridePendingTransition(0, 0)
                    true
                }
                R.id.bottom_businesses -> {
                    startActivity(Intent(applicationContext, BusinessActivity::class.java))
                    overridePendingTransition(0, 0)
                    true
                }
                R.id.bottom_events -> {
                    // Fetch events
                    updateEventList()
                    overridePendingTransition(0, 0)
                    true
                }
                R.id.bottom_reviews -> {
                    startActivity(Intent(applicationContext, ReviewsActivity::class.java))
                    overridePendingTransition(0, 0)
                    true
                }
                else -> false
            }
        }



        binding.searchButton.setOnClickListener {
            val location = binding.searchEditText.text.toString()
            eventViewModel.searchNearbyEvents(location, null, null)
        }
    }

    private fun updateEventList() {
        val apiService = RetrofitProvider.retrofitInstance.create(ApiService::class.java)
        val apiRepository = ApiRepository(apiService)


        eventViewModel.eventsData.observe(this, Observer { events ->
            if (events != null) {
                // Update UI
                val adapter = binding.recyclerView.adapter as EventsAdapter
                adapter.events = events
                adapter.notifyDataSetChanged()
            }
        })

        // todo: set lat long from current location
        eventViewModel.searchNearbyEvents(null, 45.8, 77.9)
    }

}
