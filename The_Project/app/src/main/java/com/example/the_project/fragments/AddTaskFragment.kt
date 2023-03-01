package com.example.the_project.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.the_project.MyApplication
import com.example.the_project.R
import com.example.the_project.databinding.FragmentAddTaskBinding
import com.example.the_project.model.AddTaskRequest
import com.example.the_project.model.AddTaskResult
import com.example.the_project.model.LoginResult
import com.example.the_project.repository.TrackerRepository
import com.example.the_project.viewmodel.AddTaskViewModel
import com.example.the_project.viewmodel.AddTaskViewModelFactory
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.launch


class AddTaskFragment : Fragment() {

    private var _binding : FragmentAddTaskBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private lateinit var addtaskTitle : EditText
    private lateinit var addtaskAsigned  : EditText
    private lateinit var addtaskDescription : EditText
    private lateinit var addtaskdeadline : EditText
    private lateinit var addtaskpriority : EditText
    private lateinit var addtaskdepartmentid: EditText
    private lateinit var addtaskCreateButton : Button
    private lateinit var addtaskstatus: EditText

    private lateinit var addTaskViewModel : AddTaskViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = AddTaskViewModelFactory(this.requireContext(), TrackerRepository())
        addTaskViewModel = ViewModelProvider(this, factory).get(AddTaskViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddTaskBinding.inflate(inflater, container, false)
        val view = binding.root

        getViews()

        addtaskCreateButton.setOnClickListener {
            val addtasktitle = addtaskTitle.text.toString().trim()
            val addtaskdescription = addtaskDescription.text.toString().trim()
            val addtaskdeadline = addtaskdeadline.text.toString().toInt()
            val addtaskasigned = addtaskAsigned.text.toString().toInt()
            val addtaskpriority = addtaskpriority.text.toString().toInt()
            val addtaskdepartmentid = addtaskdepartmentid.text.toString().toInt()
            val addtaskstatus = addtaskstatus.text.toString().toInt()
            if (addtasktitle.isEmpty() || addtaskdescription.isEmpty()) {
                Toast.makeText(
                    this.requireContext(),
                    "Please, fill up all fields!",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                addTaskViewModel.addTaskToDatabase(
                    AddTaskRequest(
                        addtasktitle, addtaskdescription, addtaskasigned,
                        addtaskpriority, addtaskdeadline, addtaskdepartmentid, addtaskstatus
                    )
                )
            }
        }

        addTaskViewModel.addtaskResult.observe(viewLifecycleOwner) {
            // Save data to preferences
            if ( it == AddTaskResult.SUCCESS ) {
                val prefs = requireActivity().getPreferences(Context.MODE_PRIVATE)
                val edit = prefs.edit()
                edit.apply()
                findNavController().navigate(R.id.action_addTaskFragment_to_myTaskScreenFragment)
            }
        }


        return view
    }

    fun getViews(){
        addtaskTitle = binding.addtasktitle
        addtaskAsigned = binding.addtaskasigned
        addtaskdeadline = binding.addtaskdeadline
        addtaskDescription = binding.addtaskdescription
        addtaskpriority = binding.addtaskpriority
        addtaskCreateButton = binding.addtaskCreatebutton
        addtaskdepartmentid = binding.addtaskdepartmentid
        addtaskstatus = binding.addtaskstatus
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}