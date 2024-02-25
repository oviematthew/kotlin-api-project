package com.example.kotlin_api_project.gMapActivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.kotlin_api_project.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity() {

    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        // Set up custom action bar
        setUpActionBar()

        initMap()

    }

    private fun setUpActionBar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // Enable back button
    }

    private fun initMap() {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync { googleMap ->
            map = googleMap

            // Enable location tracking and update the map
            enableMyLocation()

        }
    }

    private fun enableMyLocation() {
        // Enable the "My Location" button on the map
        map.isMyLocationEnabled = true

        // Move the camera to a default location (if needed)
        val defaultLocation = LatLng(0.0, 0.0)
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultLocation, 10f))

        // Add a marker at the default location
        val marker = map.addMarker(
            MarkerOptions().position(defaultLocation).title("Default Location")
        )
        marker?.tag = defaultLocation
    }

    // Handle back button click
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


//END OF CODE
}