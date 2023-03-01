package com.example.the_project.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Department (
    var ID: Int,
    var name: String
    )


