package com.example.geideatask.feature.users.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.geideatask.feature.users.data.database.User.Companion.Users_Table_Name

@Entity(tableName = Users_Table_Name)
data class User(
    @PrimaryKey val id: Int,
    val avatar: String,
    val email: String,
    val first_name: String,
    val last_name: String
) {
    companion object {
        const val Users_Table_Name = "users"
    }
}