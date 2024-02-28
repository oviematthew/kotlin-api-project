package com.example.kotlin_api_project

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.chivorn.smartmaterialspinner.SmartMaterialSpinner
import com.example.kotlin_api_project.databinding.ActivityCategoriesBinding
import com.example.kotlin_api_project.model.Category
import com.example.kotlin_api_project.repository.ApiRepository
import com.example.kotlin_api_project.viewmodel.CategoryViewModel
import com.example.kotlin_api_project.viewmodel.BusinessesViewModel
import com.example.kotlin_api_project.adapter.BusinessAdapter
import android.widget.AdapterView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class CategoriesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoriesBinding
    private val categoryViewModel = CategoryViewModel()
    private val businessesViewModel = BusinessesViewModel()
    private var selectedCategoryAlias: String? = null
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCategoriesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        val bottomNavigationView = binding.bottomNavigationView
        bottomNavigationView.selectedItemId = R.id.bottom_reviews

        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.bottom_about -> {
                    startActivity(Intent(applicationContext, AboutActivity::class.java))
                    true
                }
                R.id.bottom_businesses -> {
                    val intent = Intent(applicationContext, BusinessActivity::class.java)
                    intent.putExtra("CATEGORY_ALIAS", selectedCategoryAlias)
                    startActivity(intent)
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

        categoryViewModel.fetchCategories(ApiRepository.apiKey)
        categoryViewModel.categoryLiveData.observe(this) { categories ->
            if (categories != null) {
                val categoryTitles = categories.map { it.title }
                binding.spinner.setItem(categoryTitles)

                binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(adapterView: AdapterView<*>, view: View, position: Int, id: Long) {
                        selectedCategoryAlias = categories[position].alias
                    }

                    override fun onNothingSelected(adapterView: AdapterView<*>) {}
                }
            } else {
                Log.e("CategoriesActivity", "Error: categories is null")
            }
        }

        binding.btnGetBusinesses.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 0)
            } else {
                fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                    if (location != null) {
                        val latitude = location.latitude
                        val longitude = location.longitude
                        selectedCategoryAlias?.let { it1 ->
                            businessesViewModel.fetchBusinesses(ApiRepository.apiKey, "$latitude,$longitude", it1)
                        }
                    }
                }
            }
        }

        businessesViewModel.businessesLiveData.observe(this) { businesses ->
            if (businesses != null) {
                val businessAdapter = BusinessAdapter(businesses)
                binding.rvBusinesses.layoutManager = LinearLayoutManager(this)
                binding.rvBusinesses.adapter = businessAdapter
            } else {
                Log.e("CategoriesActivity", "Error: businesses is null")
            }
        }

    }
}
