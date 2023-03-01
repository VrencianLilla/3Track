package com.example.the_project.model

import androidx.annotation.Nullable
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(
//    var ID: Long,
    var last_name: String,
    var first_name: String,
    var email: String,
//    var type: Int,
    @Nullable
    var location: String?,
    @Nullable
    var phone_number: String?,
 //   var department_id: Int,
    var image: String
)

@JsonClass(generateAdapter = true)
data class UpdateProfileRequest(var lastName: String,
                                var firstName: String,
                                var location: String,
                                var phoneNumber: String,
                                var imageUrl: String )

@JsonClass(generateAdapter = true)
data class UpdateProfileResponse(var message: String)



