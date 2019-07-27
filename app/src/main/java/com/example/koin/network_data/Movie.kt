package com.example.koin.network_data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.koin.room.ReviewConverter
import com.example.koin.room.TrailerConverter
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movie_table")
data class Movie(
    @PrimaryKey
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("title") val title: String,
    @field: SerializedName("overview") val description: String,
    @field:SerializedName("release_date") val releaseDate: String,
    @field:SerializedName("vote_average") val rating: String,
    @field:SerializedName("poster_path") val posterPath: String?=null,

    @TypeConverters(TrailerConverter::class)
    var reviewList: ReviewList?=null,

    @TypeConverters(ReviewConverter::class)
    var trailerList: TrailerList?=null
)