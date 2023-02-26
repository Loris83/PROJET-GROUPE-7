package com.example.projet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.*
import androidx.navigation.ui.*
import com.example.projet.DatabaseHelper.Companion.database
import com.example.projet.databinding.ActivityMainBinding
import com.example.projet.user.UserSession

import com.google.android.material.navigation.NavigationView
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    public lateinit var binding : ActivityMainBinding
    private lateinit var appBarConfiguration : AppBarConfiguration
    private val database = DatabaseHelper.database

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        navView = binding.navView
        setContentView(binding.root)
        this.setup()
    }
    private fun setup(){

        setSupportActionBar(binding.appBarMain.toolbar)


        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        val drawerLayout : DrawerLayout = binding.activityMainLayout

        navController.addOnDestinationChangedListener { nc: NavController, nd: NavDestination, bundle: Bundle? ->
            setupMenu()
        }

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,
                R.id.nav_reservation,
                R.id.nav_list_users,
                R.id.nav_login,
                R.id.nav_reserver,
                R.id.nav_logout
            ),  drawerLayout
        )

        val actionBarDrawerToggle = ActionBarDrawerToggle(this, binding.activityMainLayout, binding.appBarMain.toolbar,R.string.app_name, R.string.app_name)
        binding.activityMainLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        NavigationUI.setupActionBarWithNavController(this,navController,appBarConfiguration)
        NavigationUI.setupWithNavController(binding.navView,navController)

        //navView.menu.findItem(R.id.nav_list_users)?.isVisible = false

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


    companion object {
        public var navView: NavigationView? = null
        public fun setupMenu(){
            if(navView == null)return
            navView?.apply {
                if (UserSession.logged){
                    menu.findItem(R.id.nav_list_users).isVisible = UserSession.admin
                    menu.findItem(R.id.nav_reservation).isVisible = true
                    menu.findItem(R.id.nav_reserver).isVisible = true
                    menu.findItem(R.id.nav_login).isVisible = false
                    menu.findItem(R.id.nav_logout).isVisible = true
                }else{
                    menu.findItem(R.id.nav_list_users).isVisible = false
                    menu.findItem(R.id.nav_reservation).isVisible = false
                    menu.findItem(R.id.nav_reserver).isVisible = false
                    menu.findItem(R.id.nav_login).isVisible = true
                    menu.findItem(R.id.nav_logout).isVisible = false
                }

            }
        }
    }




}