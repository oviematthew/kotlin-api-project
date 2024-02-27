package com.example.kotlin_api_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlin_api_project.adapter.BusinessAdapter
import com.example.kotlin_api_project.databinding.ActivityAboutBinding
import com.example.kotlin_api_project.databinding.ActivityBusinessBinding
import com.example.kotlin_api_project.model.Businesses
import com.example.kotlin_api_project.viewmodel.BusinessesViewModel

class BusinessActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBusinessBinding
    private lateinit var businessesViewModel: BusinessesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the activity and set the contentView to the root of the xml
        binding = ActivityBusinessBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //items in xml to use for search and location buttons
        var locationBtn = binding.locationBtn
        var searchField = binding.searchLocationtext
        var searchBtn = binding.searchBtn


        // Retrieve latitude and longitude from Intent extras
        val intent = intent
        if (intent.hasExtra("LATITUDE") && intent.hasExtra("LONGITUDE")) {
            val latitude = intent.getDoubleExtra("LATITUDE", 0.0)
            val longitude = intent.getDoubleExtra("LONGITUDE", 0.0)

            // Set a click listener for locationBtn
            locationBtn.setOnClickListener {
                // Update the text of searchLocationtext on click
//                searchField.text.toString() = "$latitude, $longitude"
            }
        }

        // Access views using binding
        val bottomNavigationView = binding.bottomNavigationView
        bottomNavigationView.selectedItemId = R.id.bottom_businesses

        // Set a listener for item selection
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.bottom_about -> {
                    startActivity(Intent(applicationContext, AboutActivity::class.java))
                    true
                }
                R.id.bottom_businesses -> {
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


    }


}