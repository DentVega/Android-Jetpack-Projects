package com.brianvega.startupweekend.activities.main

import android.Manifest
import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import com.brianvega.startupweekend.PermissionApp
import com.brianvega.startupweekend.R
import com.brianvega.startupweekend.activities.BaseActivity
import com.brianvega.startupweekend.fragments.about.AboutFragment
import com.brianvega.startupweekend.fragments.address.AddressFragment
import com.brianvega.startupweekend.fragments.events.EventsFragment
import com.brianvega.startupweekend.fragments.home.HomeFragment

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val requestPermissionAccessPhoneState = 1
    private lateinit var arrayPermissions: List<PermissionApp>

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)
        loadReferences()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkPermissions()
        }
    }

    private fun loadReferences() {
        loadFragment(HomeFragment.newInstance(), R.id.frame_container)
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> {
                loadFragment(HomeFragment.newInstance(), R.id.frame_container)
            }
            R.id.nav_about -> {
                loadFragment(AboutFragment.newInstance(), R.id.frame_container)
            }
            R.id.nav_events -> {
                loadFragment(EventsFragment.newInstance(), R.id.frame_container)
            }
            R.id.nav_location -> {
                if (checkPermissions()) {
                    loadFragment(AddressFragment.newInstance(), R.id.frame_container)
                } else {
                    showCustomToast(getString(R.string.accpet_permissions))
                }
            }
            R.id.nav_share -> {

            }
            R.id.nav_info -> {

            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun checkPermissions(): Boolean {
        arrayPermissions = listOf(
            PermissionApp(
                checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION),
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        )

        var isPermissionChecked = true
        for (permissions in arrayPermissions) {
            if (permissions.permissionAceppted != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(permissions.permissionName),
                    requestPermissionAccessPhoneState
                )
                isPermissionChecked = false
            }
        }
        return isPermissionChecked
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            requestPermissionAccessPhoneState -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    showCustomToast(getString(R.string.accpet_permissions))
                } else {
                    checkPermissions()
                }
            }
        }
    }

}
