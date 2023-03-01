package com.example.the_project.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginRequest(var email: String, var passwordKey: String )
