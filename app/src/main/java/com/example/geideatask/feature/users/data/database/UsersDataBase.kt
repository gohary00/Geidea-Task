package com.example.geideatask.feature.users.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1)
abstract class UsersDataBase : RoomDatabase() {
    abstract fun userDao(): UserDao
}