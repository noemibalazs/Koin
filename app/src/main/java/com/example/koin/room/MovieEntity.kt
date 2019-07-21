package com.example.koin.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_table")
class MovieEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val description: String,
    val releaseDate: String,
    val rating: String,
    val poster: String
)