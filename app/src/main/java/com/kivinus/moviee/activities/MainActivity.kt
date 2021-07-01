package com.kivinus.moviee.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.kivinus.moviee.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var bottomNavigation: BottomNavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigation = findViewById(R.id.bottomNavigation)
        setupNav()
    }

    private fun setupNav() {
        val navController = findNavController(R.id.fragmentContainerView)
        bottomNavigation!!.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.favoriteFragment -> showBottomNav()
                R.id.homeFragment -> showBottomNav()
                R.id.watchLaterFragment -> showBottomNav()
                else -> hideBottomNav() } }
    }

    private fun showBottomNav() { bottomNavigation!!.visibility = View.VISIBLE }
    private fun hideBottomNav() { bottomNavigation!!.visibility = View.GONE }

}



