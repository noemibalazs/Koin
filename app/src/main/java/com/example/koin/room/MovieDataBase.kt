package com.example.koin.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.koin.network_data.Movie

@Database(entities = [Movie::class], exportSchema = false, version = 2)
@TypeConverters(ReviewConverter::class, TrailerConverter::class)
abstract class MovieDataBase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}