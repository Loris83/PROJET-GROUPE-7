package com.example.projet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.Window
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import com.example.projet.DatabaseHelper.Companion.database
import com.example.projet.databinding.ActivityMainBinding

import com.google.android.material.navigation.NavigationView
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var appBarConfiguration : AppBarConfiguration
    private val database = DatabaseHelper.database
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.setup()
    }
    private fun setup(){
        setSupportActionBar(binding.appBarMain.toolbar)

        val navView : NavigationView = binding.navView
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        val drawerLayout : DrawerLayout = binding.activityMainLayout

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,
                R.id.nav_reservation,
                R.id.nav_list_users,
                R.id.nav_login,
                R.id.nav_reserver
            ),  drawerLayout
        )


       /* navView.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.nav_home -> {
                    true
                }
                else -> false
            }
        }*/

        val actionBarDrawerToggle = ActionBarDrawerToggle(this, binding.activityMainLayout, binding.appBarMain.toolbar,R.string.app_name, R.string.app_name)
        binding.activityMainLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        NavigationUI.setupActionBarWithNavController(this,navController,appBarConfiguration)
        NavigationUI.setupWithNavController(navView,navController)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        return NavigationUI.navigateUp(navController,appBarConfiguration)
        //return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }



}