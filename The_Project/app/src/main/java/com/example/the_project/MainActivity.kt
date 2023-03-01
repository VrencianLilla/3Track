package com.example.the_project

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.the_project.model.LoginResponse
import com.example.the_project.model.LoginResult
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    companion object {

        fun loadValuesFromSharedPreferences(sharedPreferences: SharedPreferences) {

            MyApplication.token = sharedPreferences.getString("token", "").toString()
            MyApplication.deadline = sharedPreferences.getLong("deadline", 0)
        }

        fun saveValuesToSharedPreferences(sharedPreferences: SharedPreferences) {
            val editor = sharedPreferences.edit()
            editor.putString("token", MyApplication.token)
            editor.putLong("deadline", MyApplication.deadline)
            editor.apply()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val controller = findNavController(R.id.nav_host_fragment)

    }

}