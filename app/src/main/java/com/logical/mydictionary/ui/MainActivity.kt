package com.logical.mydictionary.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.logical.mydictionary.R
import com.logical.mydictionary.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.navController

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.searchFragment,
                R.id.savedFragment
            )
        )

        binding.bottomNavigationView.setupWithNavController(navController)
        setupActionBarWithNavController(navController, appBarConfiguration)


        /*      binding.bottomNavigationView.setOnItemSelectedListener { menuItem ->
                  Log.e("output", "inside listener")
                  when (menuItem.itemId) {
                      R.id.searchFragment -> {
                          Log.e("output", "inside search")

                          replaceCurrentFragment(SearchFragment())
                          supportActionBar?.hide()
                          true
                      }
                      R.id.savedFragment -> {
                          Log.e("output", "inside saved")

                          replaceCurrentFragment(SavedFragment())
                          supportActionBar?.hide()
                          true
                      }
                      else -> {
                          Log.e("output", "inside else")

                          false
                      }
                  }
              }

      */
        //hideActionBar()
        //supportActionBar?.hide()
        setContentView(binding.root)
    }

    /*private fun replaceCurrentFragment(fragment: Fragment) =

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.navHostFragment, fragment)
            commit()
            Log.e("output", "inside replace")


        }*/


    override fun onSupportNavigateUp(): Boolean =
        navController.navigateUp() || super.onSupportNavigateUp()

    /*  private fun hideActionBar() {
          navController.addOnDestinationChangedListener { _, destination, _ ->
              when (destination.id) {
                  R.id.searchFragment -> {
                      supportActionBar?.hide()
                  }
                  R.id.savedFragment -> {
                      supportActionBar?.hide()
                  }
                  else -> {
                      supportActionBar?.show()
                  }
              }
          }
      }
  }*/

}