package com.example.kotlin_api_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlin_api_project.databinding.ActivityBusinessBinding
import com.example.kotlin_api_project.databinding.ActivityEventsBinding

class EventsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEventsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the activity and set the contentView to the root of the xml
        binding = ActivityEventsBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
    }
}