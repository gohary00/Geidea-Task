package com.example.geideatask.hilt

import com.example.geideatask.feature.users.data.api.UsersApiService
import com.example.geideatask.feature.users.data.database.UserDao
import com.example.geideatask.feature.users.data.repos.UsersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepoModule @Inject constructor() {

    @Singleton
    @Provides
    fun provideUsersRepository(
        apiService: UsersApiService, userDao: UserDao
    ): UsersRepository = UsersRepository(apiService, userDao)

}


