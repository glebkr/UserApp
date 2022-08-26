package com.example.myapplication.repo

import androidx.lifecycle.LiveData
import com.example.myapplication.room.RoomDAO
import com.example.myapplication.room.UserEntity

class Repository (private val roomDAO: RoomDAO) {
    fun getUserList() : LiveData<List<UserEntity>> = roomDAO.getUserList()
    fun addUser(user: UserEntity) = roomDAO.addUser(user)
}