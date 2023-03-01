package com.example.the_project.model

import android.os.Parcelable
import androidx.annotation.Nullable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Task(
    var title : String,
    var description: String,
    var created_by_user_ID: Int,
    var asigned_to_user_ID: Int,
    var priority: Int,
    @Nullable
    var deadline: Int?,
    @Nullable
    var department_ID: Int?,
    var status: Int
): Parcelable

@JsonClass(generateAdapter = true)
data class AddTaskRequest(var title: String,
                          var description: String,
                          var assignedToUserId: Int,
                          var priority: Int,
                          @Nullable
                          var deadline: Int?,
                          var departmentId: Int,
                          var status: Int
)

@JsonClass(generateAdapter = true)
data class AddTaskResponse( var message: String)