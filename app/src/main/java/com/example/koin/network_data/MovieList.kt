package com.example.koin.network_data

import com.google.gson.annotations.SerializedName

data class MovieList(
    @field:SerializedName("results") val movieList: List<Movie>
)