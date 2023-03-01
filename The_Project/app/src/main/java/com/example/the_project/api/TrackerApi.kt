package com.example.the_project.api

import com.example.the_project.model.*
import com.example.the_project.util.Constants
import retrofit2.Response
import retrofit2.http.*

interface TrackerApi {
    @POST(Constants.LOGIN_URL)
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @GET(Constants.GET_USERS_URL)
    suspend fun getUsers(@Header("token") token: String): Response<List<User>>
    @GET(Constants.GET_USER_URL)
    suspend fun getUser(@Header("token") token: String): Response<User>

    @GET(Constants.GET_TASKS_URL)
    suspend fun getTasks(@Header("token") token: String): Response<List<Task>>

    @GET(Constants.GET_ACTIVITIES_URL)
    suspend fun getActivites(@Header("token") token: String): Response<List<Activitie>>

    @POST(Constants.UPDATE_PROFILE_URL)
    suspend fun updateProfile(@Header("token") token: String, @Body request: UpdateProfileRequest): Response<UpdateProfileResponse>

    @POST(Constants.ADD_TASK)
    suspend fun addTask(@Header("token") token: String, @Body request: AddTaskRequest): Response<AddTaskResponse>

    @GET(Constants.GET_DEPARTMENTS)
    suspend fun getDepartments(@Header("token") token: String): Response<List<Department>>

}
