package com.example.geideatask.feature.users.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.geideatask.feature.users.data.models.local.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class UsersDataBase : RoomDatabase() {
    abstract fun userDao(): UserDao
}