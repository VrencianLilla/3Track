package com.example.the_project.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.the_project.MyApplication
import com.example.the_project.MyGroupDataAdapter
import com.example.the_project.R
import com.example.the_project.databinding.FragmentUpdateProfileBinding
import com.example.the_project.model.*
import com.example.the_project.repository.TrackerRepository
import com.example.the_project.viewmodel.*
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit


class UpdateProfileFragment : Fragment() {
    private var _binding : FragmentUpdateProfileBinding? = null
    private val binding get() = _binding!!

    private val profViewModel : ProfileViewModel by activityViewModels()
    private val updateprofViewModel: UpdateProfileViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpdateProfileBinding.inflate(inflater, container, false)
        val view = binding.root

        profViewModel.getUserInformation()
        profViewModel.user.observe(viewLifecycleOwner){
            val profuser = profViewModel.user.value

            binding.profileEditFirstName.setText(profuser!!.first_name)
            binding.profileEditLastName.setText(profuser!!.last_name)
            binding.profileEditPhoneNumber.setText(profuser!!.phone_number)
            binding.profileEditLocation.setText(profuser!!.location)
            binding.profileEditImageUrl.setText(profuser!!.image)
        }

        binding.button.setOnClickListener {
            val profeditfirstname = binding.profileEditFirstName.text.toString()
            val profeditlasttname = binding.profileEditLastName.text.toString()
            val profeditphonenumber = binding.profileEditPhoneNumber.text.toString()
            val profeditlocation = binding.profileEditLocation.text.toString()
            val profeditimage = binding.profileEditImageUrl.text.toString()

            updateprofViewModel.updateprofile(
                UpdateProfileRequest(
                    profeditlasttname, profeditfirstname, profeditlocation, profeditphonenumber, profeditimage)
            )

        }

        updateprofViewModel.updateprofileResult.observe(viewLifecycleOwner) {
            // Save data to preferences
            if ( it == UpdateProfileResult.SUCCESS ) {
                val prefs = requireActivity().getPreferences(Context.MODE_PRIVATE)
                val edit = prefs.edit()
                edit.apply()
                findNavController().navigate(R.id.action_updateProfileFragment_to_viewProfileFragment)
            }
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}