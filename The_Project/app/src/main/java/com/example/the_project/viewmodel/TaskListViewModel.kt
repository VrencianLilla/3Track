package com.example.the_project.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.the_project.MyApplication
import com.example.the_project.model.Task
import com.example.the_project.repository.TrackerRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class TaskListViewModelFactory(private val context: Context, private val repository: TrackerRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TaskListViewModel(context, repository) as T
    }
}

class TaskListViewModel(val context: Context, val repository: TrackerRepository) : ViewModel() {
    var taskList : MutableLiveData<List<Task>> = MutableLiveData()

    fun getTasks() {
        viewModelScope.launch {
            try {
                val response = repository.getTasks(MyApplication.token)
                if(response.isSuccessful) {
                    taskList.value = response.body()
                }else{
                    Log.i("xxx-uvm", response.message())
                }
            } catch (e: Exception) {
                Log.i("xxx", e.toString())
            }
        }
    }
}