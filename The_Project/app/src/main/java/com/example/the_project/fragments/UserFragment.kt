package com.example.the_project.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.the_project.R
import com.example.the_project.databinding.FragmentUsersBinding
import com.example.the_project.model.User
import com.example.the_project.repository.TrackerRepository
import com.example.the_project.viewmodel.UserListViewModel
import com.example.the_project.viewmodel.UserListViewModelFactory


class UserFragment : Fragment() {

    private var _binding : FragmentUsersBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private lateinit var userListViewModel: UserListViewModel
    private lateinit var recycler_view: RecyclerView
    private lateinit var adapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUsersBinding.inflate(inflater, container, false)
        val view = binding.root


        val factory = UserListViewModelFactory(requireContext(), TrackerRepository())
        userListViewModel = ViewModelProvider(this, factory).get(UserListViewModel::class.java)

        recycler_view = binding.recyclerView2
        setupRecyclerView()
        userListViewModel.getUsers()
        userListViewModel.userList.observe(viewLifecycleOwner){
            adapter.setData(userListViewModel.userList.value as ArrayList<User>)
            adapter.notifyDataSetChanged()
        }

        return view
    }

    private fun setupRecyclerView(){
        Log.d("myUserFragxxx", "idaig eljut5!")
        adapter = UserAdapter(ArrayList<User>(), this.requireContext())
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(requireContext())
        recycler_view.setHasFixedSize(true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}