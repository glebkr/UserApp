package com.example.myapplication.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RoomDAO {
    @Query("SELECT * FROM User")
    fun getUserList() : LiveData<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addUser(user: UserEntity)
}