package com.example.myapplication.main

import com.example.myapplication.room.UserEntity

interface ISelectUser {
    fun selectUser(user: UserEntity)
}