package com.example.koin.repository

import androidx.lifecycle.LiveData
import com.example.koin.helper.SharedPrefHelper
import com.example.koin.network.MovieService
import com.example.koin.network_data.ReviewList
import com.example.koin.util.KEY

class ReviewRepository(service: MovieService, sharedPrefHelper: SharedPrefHelper): BaseRepository<ReviewList>(service, sharedPrefHelper) {
    override fun loadMovies(): LiveData<ReviewList> {
        return fetchData { service.getReviewList(sharedPrefHelper.getMovieId(), KEY) }
    }
}