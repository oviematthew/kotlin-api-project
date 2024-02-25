package com.example.kotlin_api_project

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlin_api_project.R
import com.example.kotlin_api_project.databinding.ActivityAboutBinding
import com.example.kotlin_api_project.gMapActivity.MapsActivity
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView

class AboutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAboutBinding
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the activity and set the contentView to the root of the xml
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btnViewLoc = binding.btnViewLoc
        btnViewLoc.setOnClickListener {
            val intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)
        }

        // Access views using binding
        val bottomNavigationView = binding.bottomNavigationView
        bottomNavigationView.selectedItemId = R.id.bottom_about

        // Set a listener for item selection
//        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            bottomNavigationView.setOnNavigationItemSelectedListener() { menuItem ->
            when (menuItem.itemId) {
                R.id.bottom_about -> {
                    overridePendingTransition(0, 0)
                    true
                }
                R.id.bottom_businesses -> {
                    startActivity(Intent(applicationContext, BusinessActivity::class.java))
                    overridePendingTransition(0, 0)
                    true
                }
                R.id.bottom_events -> {
                    startActivity(Intent(applicationContext, EventsActivity::class.java))
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