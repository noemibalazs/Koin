package com.example.koin.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.koin.network_data.Movie

@Database(entities = [MovieEntity::class], exportSchema = false, version = 4)
@TypeConverters(ReviewConverter::class, TrailerConverter::class)
abstract class MovieDataBase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}