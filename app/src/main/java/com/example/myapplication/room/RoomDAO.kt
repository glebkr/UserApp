package com.example.myapplication.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RoomDAO {
    @Query("SELECT * FROM User")
    fun getUserList() : LiveData<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addUser(user: UserEntity)

    @Delete
    fun deleteUser(user: UserEntity)

    /*
    @Query("")
    fun replaceUser(fromPosition: Int, toPosition: Int, user: UserEntity)

     */
}