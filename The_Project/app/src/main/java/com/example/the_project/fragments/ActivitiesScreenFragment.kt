package com.example.the_project.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.the_project.R
import com.example.the_project.databinding.FragmentActivitiesScreenBinding
import com.example.the_project.databinding.FragmentMyTaskScreenBinding
import com.example.the_project.model.Activitie
import com.example.the_project.model.Task
import com.example.the_project.repository.TrackerRepository
import com.example.the_project.viewmodel.*
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView


class ActivitiesScreenFragment : Fragment() {

    private var _binding : FragmentActivitiesScreenBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private lateinit var activitieListViewModel : ActivitieListViewModel
    private lateinit var recycler_view: RecyclerView
    private lateinit var adapter: ActivitieAdapter
    private lateinit var backbutton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentActivitiesScreenBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.bottomnav.setOnItemSelectedListener {
            when( it.itemId ){
                R.id.activities -> findNavController().navigate(R.id.action_activitiesScreenFragment_self)
                R.id.my_task -> findNavController().navigate(R.id.action_activitiesScreenFragment_to_myTaskScreenFragment)
                R.id.my_groups -> findNavController().navigate(R.id.action_activitiesScreenFragment_to_myGroupsFragment)
                R.id.settings -> findNavController().navigate(R.id.action_activitiesScreenFragment_to_settingsFragment)

                else -> {

                }


            }
            true
        }

        val factory = ActivitieListViewModelFactory(requireContext(), TrackerRepository())
        activitieListViewModel = ViewModelProvider(this, factory).get(ActivitieListViewModel::class.java)

        recycler_view = binding.recyclerView
        setupRecyclerView()
        activitieListViewModel.getActivities()
        activitieListViewModel.activitieList.observe(viewLifecycleOwner){
            adapter.setData(activitieListViewModel.activitieList.value as ArrayList<Activitie>)
            adapter.notifyDataSetChanged()
        }

        backbutton = binding.activitieBackButton
        backbutton.setOnClickListener {
            findNavController().navigate(R.id.action_activitiesScreenFragment_to_loginFragment)
        }

        return view
    }

    private fun setupRecyclerView(){
        Log.d("ActivitieFragxxx", "idaig eljut!")
        adapter = ActivitieAdapter(ArrayList<Activitie>(), this.requireContext())
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(requireContext())
        recycler_view.setHasFixedSize(true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}