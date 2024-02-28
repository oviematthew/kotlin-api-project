    package com.example.kotlin_api_project.gMapActivity

    import android.content.pm.PackageManager
    import android.os.Bundle
    import androidx.appcompat.app.AppCompatActivity
    import androidx.appcompat.widget.Toolbar
    import androidx.core.app.ActivityCompat
    import com.example.kotlin_api_project.R
    import com.google.android.gms.maps.CameraUpdateFactory
    import com.google.android.gms.maps.GoogleMap
    import com.google.android.gms.maps.SupportMapFragment
    import com.google.android.gms.maps.model.LatLng
    import com.google.android.gms.maps.model.MarkerOptions

    import android.location.Location
    import android.widget.Toast
    import com.google.android.gms.location.FusedLocationProviderClient
    import com.google.android.gms.location.LocationServices
    import com.google.android.gms.maps.OnMapReadyCallback
    import com.google.android.gms.maps.model.BitmapDescriptorFactory
    import com.google.android.gms.tasks.Task


    class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

        private val FINE_PERMISSION_CODE = 1
        private var currentLocation: Location? = null
        private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

        override fun onCreate( savedInstanceState: Bundle? ) {

            super.onCreate( savedInstanceState )
            setContentView( R.layout.activity_maps )

            // **Initialize FusedLocationProviderClient for location services
            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient( this )

            // **Get last known location
            getLastLocation()

            // **Set up custom action bar
            setUpActionBar()

        }

        // **Method to set up custom action bar with back button
        private fun setUpActionBar() {

            val toolbar = findViewById<Toolbar>( R.id.toolbar )
            setSupportActionBar( toolbar )
            supportActionBar?.setDisplayHomeAsUpEnabled( true ) // Enable back button

        }

        // **Method to get the last known location of the device
        private fun getLastLocation() {

            if ( ActivityCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                 ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                 ) != PackageManager.PERMISSION_GRANTED
             ) {

                // **Request location permission if not granted
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf( android.Manifest.permission.ACCESS_FINE_LOCATION ),
                    FINE_PERMISSION_CODE
                 )
                return

            }
            // **Get last known location from FusedLocationProviderClient
            val task: Task<Location> = fusedLocationProviderClient.lastLocation
            task.addOnSuccessListener { location ->
                if (location != null) {

                    currentLocation = location
                    // **Once location is obtained, initialize the map
                    val mapFragment =
                        supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
                    mapFragment.getMapAsync(this@MapsActivity)

                }
            }
        }



        // **Callback method invoked when the map is ready
        override fun onMapReady( googleMap: GoogleMap ) {

            currentLocation?.let {
                val currentLatLng = LatLng( it.latitude, it.longitude )

                LocationManager.setCurrentLocation(it.latitude, it.longitude)

                // **Move camera to current location
                googleMap.moveCamera( CameraUpdateFactory.newLatLngZoom( currentLatLng, 15f ) )

                // **Add a marker for the current location
                val markerOptions = MarkerOptions().position( currentLatLng ).title(  currentLatLng.toString()  )
                markerOptions.icon( BitmapDescriptorFactory.defaultMarker( BitmapDescriptorFactory.HUE_BLUE ) )
                googleMap.addMarker( markerOptions )

            }
        }

        object LocationManager {
            private var currentLocation: LatLng? = null

            fun getCurrentLocation(): LatLng? {
                return currentLocation
            }

            fun setCurrentLocation(latitude: Double, longitude: Double) {
                currentLocation = LatLng(latitude, longitude)
            }
        }


        // **Callback method invoked when permissions are requested
        override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<String>,
            grantResults: IntArray
         ) {

            super.onRequestPermissionsResult( requestCode, permissions, grantResults )
            if ( requestCode == FINE_PERMISSION_CODE ) {

                if ( grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED ) {

                    // **If location permission is granted, get last known location
                    getLastLocation()

                } else {

                    // **If permission is denied, show a toast
                    Toast.makeText( this, "Location permission is denied.", Toast.LENGTH_LONG ).show()

                }
            }
        }

        // **Handle back button click
        override fun onSupportNavigateUp(): Boolean {

            onBackPressedDispatcher.onBackPressed()
            return true

        }


    //END OF CODE***
    }