package com.example.myapplication.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
class UserEntity (
    @ColumnInfo(name = "Surname") val surname: String?,
    @ColumnInfo(name = "Name") val name: String?,
    @ColumnInfo(name = "SecondName") val secondName: String?,
    @ColumnInfo(name = "Age") val age: Int?,
    @ColumnInfo(name = "Height") val height: Double?
    ) {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}