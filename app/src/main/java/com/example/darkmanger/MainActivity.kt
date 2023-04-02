package com.example.darkmanger


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView




class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    val navController = Navigation.findNavController(this, R.id.activity_main_nav_host_fragment)
    val bottomNavigationView =
        findViewById<BottomNavigationView>(R.id.activity_main_bottom_navigation_view)
    NavigationUI.setupWithNavController(bottomNavigationView, navController)
}

    override fun onBackPressed() {
       // super.onBackPressed()
        Toast.makeText(applicationContext,"this may cause lose of date are you sure ?",Toast.LENGTH_LONG).show()
    }
}