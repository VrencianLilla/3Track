package com.example.the_project.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.the_project.MyApplication
import com.example.the_project.model.LoginRequest
import com.example.the_project.model.LoginResult
import com.example.the_project.model.UpdateProfileRequest
import com.example.the_project.model.UpdateProfileResult
import com.example.the_project.repository.TrackerRepository
import kotlinx.coroutines.launch

class UpdateProfileViewModelFactory(
    private val repository: TrackerRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(repository) as T
    }
}

class UpdateProfileViewModel() : ViewModel() {

    var updateprofileResult: MutableLiveData<UpdateProfileResult> = MutableLiveData()

    fun updateprofile(request: UpdateProfileRequest) {
        viewModelScope.launch {

            try {
                val response = TrackerRepository().updateProfile(MyApplication.token, request)

                if (response.isSuccessful) {

                    updateprofileResult.value = UpdateProfileResult.SUCCESS
                    Log.i("xxx", response.body().toString())
                }
            } catch (e: Exception) {
                updateprofileResult.value = UpdateProfileResult.UNKNOWN_ERROR
                Log.i("xxx", e.toString())
            }
        }
    }


}