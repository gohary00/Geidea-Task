package com.example.geideatask.feature.users.data.repos

import com.example.geideatask.feature.users.data.api.UsersApiService
import com.example.geideatask.feature.users.data.database.User
import com.example.geideatask.feature.users.data.database.UserDao
import com.example.geideatask.feature.users.data.models.shared.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UsersRepository @Inject constructor(
    private val usersApiService: UsersApiService,
    private val userDao: UserDao
) {
    fun getAllUsers() = flow {
        // Emit Loading State
        emit(State.loading())
        try {
            // Fetch latest users from remote
            val apiResponse = usersApiService.getUsers()

            // Check for response validation
            if (apiResponse.isSuccessful) {
                // Save users into the persistence storage
                saveRemoteUser(apiResponse.body()?.data.orEmpty())
            } else {
                // Something went wrong! Emit Error staten.
                emit(State.error(apiResponse.message()))
            }
        } catch (e: Exception) {
            // Exception occurred! Emit error
            emit(State.error("Network error! Can't get latest users."))
            e.printStackTrace()
        }

        // Retrieve users from persistence storage and emit
        emitAll(userDao.getAllUsers().map {
            State.success(it)
        })
    }.flowOn(Dispatchers.IO)

    private fun saveRemoteUser(users: List<User>) = userDao.insertAll(users)


}