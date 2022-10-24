package com.example.geideatask.feature.users.data.repos

import com.example.geideatask.feature.users.data.api.UsersApiService
import com.example.geideatask.feature.users.data.models.UsersResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class UsersRepository @Inject constructor(
    private val usersApiService: UsersApiService,
) {
    private val _usersState = MutableStateFlow<UsersResponse?>(null)
    val usersState: StateFlow<UsersResponse?>
        get() = _usersState

    suspend fun getUsers() = _usersState.emit(usersApiService.getUsers())
}