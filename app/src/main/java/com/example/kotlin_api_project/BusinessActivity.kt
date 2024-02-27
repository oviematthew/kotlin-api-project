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
    private val businessList = mutableListOf<Businesses>()
    private lateinit var businessesViewModel: BusinessesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the activity and set the contentView to the root of the xml
        binding = ActivityBusinessBinding.inflate(layoutInflater)
        setContentView(binding.root)

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


        val businessesAdapter = BusinessAdapter(businessList)
    }


}