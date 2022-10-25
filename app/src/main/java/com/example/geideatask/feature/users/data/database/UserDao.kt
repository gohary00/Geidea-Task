package com.example.geideatask.feature.users.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM ${User.Users_Table_Name}")
    fun getAllUsers(): Flow<List<User>>

    @Query("SELECT * FROM users WHERE id LIKE :id")
    fun findByID(id: String): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll( users: List<User>)

}