package com.example.the_project.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.the_project.MyApplication
import com.example.the_project.model.Activitie
import com.example.the_project.repository.TrackerRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class ActivitieListViewModelFactory(private val context: Context, private val repository: TrackerRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ActivitieListViewModel(context, repository) as T
    }
}

class ActivitieListViewModel(val context: Context, val repository: TrackerRepository) : ViewModel() {
    var activitieList : MutableLiveData<List<Activitie>> = MutableLiveData()

    fun getActivities() {
        viewModelScope.launch {
            try {
                val response = repository.getActivities(MyApplication.token)
                if(response.isSuccessful) {
                    activitieList.value = response.body()
                } else{
                    Log.i("xxx-uvm", response.message())
                }
            } catch (e: Exception) {
                Log.i("xxx", e.toString())
            }
        }
    }


}