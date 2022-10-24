package com.example.geideatask.feature.users.data.api

import com.example.geideatask.feature.users.data.models.UsersResponse
import retrofit2.http.GET

interface UsersApiService {
    @GET("users?per_page=20")
    suspend fun getUsers(): UsersResponse
}