package com.example.koin.network_data

import com.google.gson.annotations.SerializedName

class ReviewList(
    @field:SerializedName("results") val reviewList: List<Review>
) {
}