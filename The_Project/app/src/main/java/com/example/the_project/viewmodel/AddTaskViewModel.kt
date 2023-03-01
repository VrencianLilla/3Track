package com.example.the_project.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.the_project.MyApplication
import com.example.the_project.model.AddTaskRequest
import com.example.the_project.model.AddTaskResult
import com.example.the_project.model.LoginResult
import com.example.the_project.repository.TrackerRepository
import kotlinx.coroutines.launch

class AddTaskViewModelFactory(
    private val context: Context, private val repository: TrackerRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AddTaskViewModel(context, repository) as T
    }
}

class AddTaskViewModel(val context: Context, val repository: TrackerRepository): ViewModel() {

    var addtaskResult: MutableLiveData<AddTaskResult> = MutableLiveData()

    fun addTaskToDatabase(request: AddTaskRequest) {
        viewModelScope.launch {
            try {
                val response = repository.addTaskToDatabase(MyApplication.token, request)
                if(response.isSuccessful) {

                    addtaskResult.value = AddTaskResult.SUCCESS
                    Log.i("xxx", response.body().toString())
                }else{
                    addtaskResult.value = AddTaskResult.INVALID_TEXTS
                    Log.i("xxx", "Invalid texts input " + response.errorBody().toString()  )
                }
            } catch (e: Exception) {
                addtaskResult.value = AddTaskResult.UNKNOWN_ERROR
                Log.d("xxx", "AddTaskViewModel exception: $e")
            }
        }
    }
}