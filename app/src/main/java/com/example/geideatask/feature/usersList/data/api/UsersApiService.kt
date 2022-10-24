package com.example.geideatask.feature.usersList.data.api

import retrofit2.http.GET

interface UsersApiService {
    @GET("users?per_page=20")
    suspend fun getUsers(): UsersApiService
}
