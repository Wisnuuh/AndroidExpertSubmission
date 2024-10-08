package com.dicoding.androidexpertsubmission

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.dicoding.androidexpertsubmission.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

//    private lateinit var broadcastReceiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,
                R.id.nav_favorite
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        // Handle the click on 'nav_favorite' menu item
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_favorite -> {
                    val uri = Uri.parse("gamesfavorite://favorites")
                    startActivity(Intent(Intent.ACTION_VIEW, uri))
                    true
                }
                else -> {
                    NavigationUI.onNavDestinationSelected(menuItem, navController)
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

//    private fun registerBroadCastReceiver() {
//        broadcastReceiver = object : BroadcastReceiver() {
//            override fun onReceive(context: Context, intent: Intent) {
//                when (intent.action) {
//                    Intent.ACTION_POWER_CONNECTED -> {
//                        Toast.makeText(this@MainActivity, "Power Connected", Toast.LENGTH_SHORT).show()
//                    }
//                    Intent.ACTION_POWER_DISCONNECTED -> {
//                        Toast.makeText(this@MainActivity, "Power Disconnected", Toast.LENGTH_SHORT).show()
//                    }
//                }
//            }
//        }
//        val intentFilter = IntentFilter()
//        intentFilter.apply {
//            addAction(Intent.ACTION_POWER_CONNECTED)
//            addAction(Intent.ACTION_POWER_DISCONNECTED)
//        }
//        registerReceiver(broadcastReceiver, intentFilter)
//    }
//
//    override fun onStart() {
//        super.onStart()
//        registerBroadCastReceiver()
//    }
//
//    override fun onStop() {
//        super.onStop()
//        unregisterReceiver(broadcastReceiver)
//    }
}