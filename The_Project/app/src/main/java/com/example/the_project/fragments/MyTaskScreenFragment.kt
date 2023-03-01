package com.example.the_project.fragments
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.the_project.R
import com.example.the_project.databinding.FragmentMyTaskScreenBinding
import com.example.the_project.model.Task
import com.example.the_project.repository.TrackerRepository
import com.example.the_project.viewmodel.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MyTaskScreenFragment : Fragment(), TaskAdapter.OnItemClickListener {

    private var _binding : FragmentMyTaskScreenBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private lateinit var taskListViewModel: TaskListViewModel
    private lateinit var recycler_view: RecyclerView
    private lateinit var adapter: TaskAdapter
    private lateinit var addBtn : FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyTaskScreenBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.bottomnav.setOnItemSelectedListener {
            when( it.itemId ){
                R.id.activities -> findNavController().navigate(R.id.action_myTaskScreenFragment_to_activitiesScreenFragment)
                R.id.my_task -> findNavController().navigate(R.id.action_myTaskScreenFragment_self2)
                R.id.my_groups -> findNavController().navigate(R.id.action_myTaskScreenFragment_to_myGroupsFragment)
                R.id.settings -> findNavController().navigate(R.id.action_myTaskScreenFragment_to_settingsFragment)

                else -> {

                }


            }
            true
        }

        val factory = TaskListViewModelFactory(requireContext(), TrackerRepository())
        taskListViewModel = ViewModelProvider(this, factory).get(TaskListViewModel::class.java)

        recycler_view = binding.recyclerView
        setupRecyclerView()
        taskListViewModel.getTasks()
        taskListViewModel.taskList.observe(viewLifecycleOwner){
            adapter.setData(taskListViewModel.taskList.value as ArrayList<Task>)
            adapter.notifyDataSetChanged()
        }

        addBtn = binding.mytaskAddbutton
        addBtn.setOnClickListener {
            findNavController().navigate(R.id.action_myTaskScreenFragment_to_addTaskFragment)
        }


        return view
    }

    private fun setupRecyclerView(){
        Log.d("myTaskFragxxx", "idaig eljut!")
        adapter = TaskAdapter(ArrayList<Task>(), this.requireContext(), this)
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(requireContext())
        recycler_view.setHasFixedSize(true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(position: Int) {
        val bundle = bundleOf("currItem" to taskListViewModel.taskList.value!![position])
        Navigation.findNavController(requireView()).navigate(R.id.action_myTaskScreenFragment_to_detailsTaskFragment, bundle)
    }

}

