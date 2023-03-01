package com.example.the_project.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.the_project.MainActivity
import com.example.the_project.MyApplication
import com.example.the_project.R
import com.example.the_project.model.LoginRequest
import com.example.the_project.model.LoginResult
import com.example.the_project.repository.TrackerRepository
import com.example.the_project.viewmodel.LoginViewModel
import com.example.the_project.viewmodel.LoginViewModelFactory


class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var editText1: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = LoginViewModelFactory(TrackerRepository())
        loginViewModel = ViewModelProvider(this, factory).get(LoginViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editText1 = view.findViewById(R.id.edittext_name_login_fragment)
        val editText2: EditText = view.findViewById(R.id.edittext_password_login_fragment)
        val button: Button = view.findViewById(R.id.button_login_fragment)

        val prefs = requireActivity().getPreferences(Context.MODE_PRIVATE)
        if (!prefs.getString("email", "").equals("")) {
            editText1.setText(prefs.getString("email", ""))
        }

        button.setOnClickListener {
            val email = editText1.text.toString().trim()
            val password = editText2.text.toString().trim()
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(
                    this.requireContext(),
                    "Please, enter your email and password",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                loginViewModel.login(LoginRequest(email, password))
            }
        }

        loginViewModel.loginResult.observe(viewLifecycleOwner) {
            // Save data to preferences
            if( it == LoginResult.INVALID_CREDENTIALS){
                Toast.makeText(
                    this.requireContext(),
                    "Invalid credentials",
                    Toast.LENGTH_LONG
                ).show()
            }
            if ( it == LoginResult.SUCCESS ) {
                //LEMENTES
                MainActivity.saveValuesToSharedPreferences(requireActivity().getPreferences(Context.MODE_PRIVATE))
                findNavController().navigate(R.id.action_loginFragment_to_activitiesScreenFragment)
            }
        }

        //Betolti az adatokat
        MainActivity.loadValuesFromSharedPreferences(requireActivity().getPreferences(Context.MODE_PRIVATE))

        loginViewModel.autologin(MyApplication.token)

    }

}