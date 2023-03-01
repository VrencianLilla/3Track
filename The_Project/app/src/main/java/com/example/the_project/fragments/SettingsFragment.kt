package com.example.the_project.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.findNavController
import com.example.the_project.MainActivity
import com.example.the_project.MyApplication
import com.example.the_project.R
import com.example.the_project.model.LoginResponse
import com.example.the_project.model.LoginResult
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView


class SettingsFragment : Fragment() {

    private lateinit var logoutbutton: Button
    private lateinit var viewprofile : Button

    private lateinit var bottomnavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        logoutbutton = view.findViewById(R.id.log_out)
        viewprofile = view.findViewById(R.id.view_profile)

        bottomnavigationView = view.findViewById(R.id.bottomnav)
        bottomnavigationView.setOnItemSelectedListener {
            when( it.itemId ){
                R.id.activities -> findNavController().navigate(R.id.action_settingsFragment_to_activitiesScreenFragment)
                R.id.my_task -> findNavController().navigate(R.id.action_settingsFragment_to_myTaskScreenFragment)
                R.id.my_groups -> findNavController().navigate(R.id.action_settingsFragment_to_myGroupsFragment)
                R.id.settings -> findNavController().navigate(R.id.action_settingsFragment_self4)

                else -> {

                }


            }
            true
        }


        logoutbutton.setOnClickListener{ logout() }
        viewprofile.setOnClickListener {
            findNavController().navigate(R.id.action_settingsFragment_to_viewProfileFragment)
        }
    }

    fun logout() {
        MyApplication.token = ""
        MyApplication.deadline = 0

        MainActivity.saveValuesToSharedPreferences(requireActivity().getPreferences(Context.MODE_PRIVATE))

        findNavController().navigate(R.id.action_settingsFragment_to_loginFragment)
    }
}