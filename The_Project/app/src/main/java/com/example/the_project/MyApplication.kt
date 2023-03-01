package com.example.the_project

import android.app.Application
import com.example.the_project.model.User

class MyApplication : Application() {
    companion object {
        var token: String = ""
        var deadline: Long = 0L
        var email: String = ""
    }
}