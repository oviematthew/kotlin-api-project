package com.example.kotlin_api_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlin_api_project.databinding.ActivityCategoriesBinding

class CategoriesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoriesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the activity and set the contentView to the root of the xml
        binding = ActivityCategoriesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Access views using binding
        val bottomNavigationView = binding.bottomNavigationView
        bottomNavigationView.selectedItemId = R.id.bottom_reviews

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
                    startActivity(Intent(applicationContext, EventsActivity::class.java))
                    true
                }
                R.id.bottom_reviews -> {
                    true
                }
                else -> false
            }
        }
    }
}
