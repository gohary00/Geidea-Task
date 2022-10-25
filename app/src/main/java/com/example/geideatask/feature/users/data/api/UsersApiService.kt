package com.example.geideatask.feature.users.data.api

import com.example.geideatask.feature.users.data.database.User
import com.example.geideatask.feature.users.data.models.remote.UsersDetailsResponse
import com.example.geideatask.feature.users.data.models.remote.UsersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UsersApiService {
    @GET("users")
    suspend fun getUsers(@Query("per_page") page: Int = 20): Response<UsersResponse>

    @GET("users/{id}")
    suspend fun getUserDetails(@Path("id") id: Int): Response<UsersDetailsResponse>
}
