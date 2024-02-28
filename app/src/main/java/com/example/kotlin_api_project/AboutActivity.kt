package com.example.kotlin_api_project

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import com.example.kotlin_api_project.databinding.ActivityAboutBinding
import com.example.kotlin_api_project.gMapActivity.MapsActivity
import com.example.kotlin_api_project.network.LocationManager
import com.google.android.gms.location.LocationServices

class AboutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAboutBinding
    private var isPermissionGranted = false

    private val requestLocationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                isPermissionGranted = true
                // Permission is granted, get the current location
                getLastLocation()
            }
        }

    private var latitude: Double = 0.0
    private var longitude: Double = 0.0

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Check location permission
        checkLocationPermission()


        // Inflate the activity and set the contentView to the root of the xml
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btnViewLoc = binding.btnViewLoc
        btnViewLoc.setOnClickListener {
            if (isPermissionGranted) {
                navigateToMapsActivity()
            } else {
                requestLocationPermission()
            }
        }

        // Access views using binding
        val bottomNavigationView = binding.bottomNavigationView
        bottomNavigationView.selectedItemId = R.id.bottom_about

        // Set a listener for item selection
        bottomNavigationView.setOnItemSelectedListener() { menuItem ->
            when (menuItem.itemId) {
                R.id.bottom_about -> {
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
                    startActivity(Intent(applicationContext, CategoriesActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }

    private fun checkLocationPermission() {
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestLocationPermission()
        } else {
            isPermissionGranted = true
            // Permission is already granted, get the current location
            getLastLocation()
        }
    }

    private fun requestLocationPermission() {
        requestLocationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    private fun navigateToMapsActivity() {
        val intent = Intent(this, MapsActivity::class.java)
        startActivity(intent)
    }

    private fun getLastLocation() {
        // Obtain the FusedLocationProviderClient to access location services
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        // Check if location permissions are granted
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Return if location permissions are not granted
            return
        }

        // Retrieve the last known location asynchronously
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                // If a location is successfully retrieved
                if (location != null) {
                    // Extract latitude and longitude from the location object
                    latitude = location.latitude
                    longitude = location.longitude

                    LocationManager.setCurrentLocation(latitude, longitude)

                    // Log the current location to Logcat

                    Log.d("location", "Current Location - Latitude: $latitude, Longitude: $longitude")

                    Log.d( "locationManager", "Current Location - Latitude: $latitude, Longitude: $longitude" )


                } else {
                    // Log an error if location retrieval fails

                    Log.e("failedLocation", "Failed to get location.")

                    Log.e( "locationManager", "Failed to get location." )


                }
            }
            .addOnFailureListener { e ->
                // Log any errors encountered during location retrieval
                Log.e("errorLocation", "Error getting location: ${e.message}")
            }
    }

    // END OF CODE
}
