package com.example.koin.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MovieEntity::class], exportSchema = false, version = 1)
abstract class MovieDataBase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}