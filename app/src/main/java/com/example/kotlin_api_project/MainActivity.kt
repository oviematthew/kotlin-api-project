package com.example.kotlin_api_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.kotlin_api_project.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity( ) {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate( savedInstanceState: Bundle? ) {
        super.onCreate( savedInstanceState )

        binding = ActivityMainBinding.inflate( layoutInflater )
        setContentView( binding.root )

        // Load HomeFragment initially
        loadFragment( HomeFragment( ), true  )

        // Access views using binding
        val bottomNavigationView = binding.mainBottomNav
        val frameLayout = binding.mainFrameLayout

        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when ( menuItem.itemId ) {
                R.id.bottomNavHome -> {
                    // Handle menu home click
                    loadFragment(  HomeFragment( ), false  )
                    true
                }
                R.id.bottomNav1 -> {
                    // Handle menu 1 click
                    loadFragment(  SecondFragment( ), false  )
                    true
                }
                R.id.bottomNav2 -> {
                    // Handle menu 2 click
                    loadFragment(  ThirdFragment( ), false  )
                    true
                }
                R.id.bottomNav3 -> {
                    // Handle menu 3 click
                    loadFragment(  FourthFragment( ), false  )
                    true
                }
                else -> true
            }

        }
    }

    private fun loadFragment( fragment: Fragment, isAppInitialized: Boolean ) {
        
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction( )

        // Conditionally handle fragment transaction
        if ( isAppInitialized ) {
            
            fragmentTransaction.add( R.id.mainFrameLayout, fragment )
            
        } else {
            
            fragmentTransaction.replace( R.id.mainFrameLayout, fragment )
            
        }

        // Add the transaction to the back stack
        fragmentTransaction.addToBackStack( null )
        fragmentTransaction.commit( )
    }
}