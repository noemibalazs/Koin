package com.example.koin.network_data

import com.google.gson.annotations.SerializedName

class TrailerList(
    @field:SerializedName("results") val trailerList: List<Trailer>
) {
}