package com.example.koin.viewmodel

import androidx.lifecycle.LiveData
import com.example.koin.network_data.ReviewList
import com.example.koin.repository.ReviewRepository

class ReviewViewModel(val reviewRepository: ReviewRepository) : BaseViewModel<ReviewList>() {
    override fun loadingMovies(): LiveData<ReviewList> {
        return reviewRepository.loadMovies()
    }
}