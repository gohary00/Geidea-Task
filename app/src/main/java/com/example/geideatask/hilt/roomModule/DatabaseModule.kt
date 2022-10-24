package com.example.geideatask.hilt.roomModule

import android.content.Context
import androidx.room.Room
import com.example.geideatask.feature.users.data.database.UserDao
import com.example.geideatask.feature.users.data.database.UsersDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): UsersDataBase {
        return Room.databaseBuilder(
            appContext,
            UsersDataBase::class.java,
            "Geidea"
        ).build()
    }

    @Provides
    fun provideChannelDao(usersDataBase: UsersDataBase): UserDao {
        return usersDataBase.userDao()
    }
}