package com.example.geideatask.feature.users.data.api

import com.example.geideatask.feature.users.data.models.remote.UsersResponse
import retrofit2.Response
import retrofit2.http.GET

interface UsersApiService {
    @GET("users?per_page=20")
    suspend fun getUsers(): Response<UsersResponse>
}
