package com.example.geideatask.feature.users.data.models.remote

data class UsersResponse(
    val data: List<User>,
    val page: Int,
    val per_page: Int,
    val support: Support,
    val total: Int,
    val total_pages: Int
)