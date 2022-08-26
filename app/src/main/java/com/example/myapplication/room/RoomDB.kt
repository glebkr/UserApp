package com.example.myapplication.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class], version = 4, exportSchema = false)
abstract class RoomDB : RoomDatabase() {
    abstract fun userDAO() : RoomDAO

    companion object {
        @Volatile
        var INSTANCE: RoomDB? = null
        fun createDatabase(context: Context?): RoomDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val db = Room.databaseBuilder(
                    context!!.applicationContext,
                    RoomDB::class.java, "db"
                )
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = db
                return db
            }
        }
    }
}