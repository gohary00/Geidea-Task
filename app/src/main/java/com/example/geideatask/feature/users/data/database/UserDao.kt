package com.example.geideatask.feature.users.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.geideatask.feature.users.data.models.local.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM userentity")
    fun getAll(): List<UserEntity>

    @Query("SELECT * FROM userentity WHERE id LIKE :id")
    fun findByID(id: String): UserEntity

    @Insert
    fun insertAll(vararg users: UserEntity)

}