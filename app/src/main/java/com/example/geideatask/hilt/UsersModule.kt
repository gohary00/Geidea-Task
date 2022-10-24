package com.example.geideatask.hilt

import com.example.geideatask.feature.users.data.api.UsersApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UsersModule {

    @Singleton
    @Provides
    fun provideUsersApiService(retrofit: Retrofit): UsersApiService =
        retrofit.create(UsersApiService::class.java)
}
