package com.example.the_project.model

import android.os.Parcelable
import androidx.annotation.Nullable
import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
data class Activitie (
//    var ID: Int,
    @Nullable
    var type: Int?,
    @Nullable
    var created_by_user_ID: Int?,
    @Nullable
    var sub_type: Int?,
    @Nullable
    var created_time: Date?,
    @Nullable
    var sub_ID: Int?,
    @Nullable
    var sub_user_ID: Int?,
    @Nullable
    var note: String?,
    @Nullable
    var progress: String?
)