package com.example.the_project.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.the_project.R
import com.example.the_project.databinding.FragmentDetailsTaskBinding
import com.example.the_project.model.Task


class DetailsTaskFragment : Fragment() {

    lateinit var currItem : Task
    lateinit var detailstasktitle: TextView
    lateinit var detailstaskdescription: TextView
    lateinit var detailstaskasigned: TextView
    lateinit var detailstaskcreatedby: TextView
    lateinit var detailstaskdeadline: TextView

    private var _binding : FragmentDetailsTaskBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsTaskBinding.inflate(inflater, container, false)
        val view = binding.root

        currItem = this.requireArguments().getParcelable<Task>("currItem")!!

        setUpView(currItem)

        return view
    }

    fun setUpView(currItem: Task){
        detailstasktitle = binding.detailtaskTitle
        detailstaskdescription = binding.detailtaskDescription
        detailstaskasigned = binding.detailtaskAsigned
        detailstaskcreatedby = binding.detailstaskCreatedBy
        detailstaskdeadline = binding.detailstaskdeadline

        detailstasktitle.text = currItem.title
        detailstaskdescription.text = currItem.description
        detailstaskasigned.text = currItem.asigned_to_user_ID.toString()
        detailstaskcreatedby.text = currItem.created_by_user_ID.toString()
        detailstaskdeadline.text = currItem.deadline.toString()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}