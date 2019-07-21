package com.example.koin.network_data

import com.google.gson.annotations.SerializedName

data class Trailer(
    @field:SerializedName("key") val key: String,
    @field:SerializedName("name") val name: String
) {
}