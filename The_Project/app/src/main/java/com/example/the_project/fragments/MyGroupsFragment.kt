package com.example.the_project.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.the_project.R
import com.google.android.material.bottomnavigation.BottomNavigationView


class MyGroupsFragment : Fragment() {
    private lateinit var general_user_groups_button : Button

    private lateinit var bottomnavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_groups, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bottomnavigationView = view.findViewById(R.id.bottomnav)
        bottomnavigationView.setOnItemSelectedListener {
            when( it.itemId ){
                R.id.activities -> findNavController().navigate(R.id.action_myGroupsFragment_to_activitiesScreenFragment)
                R.id.my_task -> findNavController().navigate(R.id.action_myGroupsFragment_to_myTaskScreenFragment)
                R.id.my_groups -> findNavController().navigate(R.id.action_myGroupsFragment_self)
                R.id.settings -> findNavController().navigate(R.id.action_myGroupsFragment_to_settingsFragment)

                else -> {

                }


            }
            true
        }

        general_user_groups_button = view.findViewById(R.id.general_groupbutton)

        general_user_groups_button.setOnClickListener {
            findNavController().navigate(R.id.action_myGroupsFragment_to_userFragment)
        }
    }


}