package com.example.koin.network_data

import com.google.gson.annotations.SerializedName

data class Review(
    @field:SerializedName("author") val author:String,
    @field:SerializedName("content") val content: String
) {
}