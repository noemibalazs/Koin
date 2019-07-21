package com.example.koin.repository

import androidx.lifecycle.LiveData
import com.example.koin.helper.SharedPrefHelper
import com.example.koin.network.MovieService
import com.example.koin.network_data.TrailerList
import com.example.koin.util.KEY

class TrailerRepository(service: MovieService, sharedPrefHelper: SharedPrefHelper): BaseRepository<TrailerList>(service, sharedPrefHelper) {

    override fun loadMovies(): LiveData<TrailerList> {
        return fetchData { service.getTrailerList(sharedPrefHelper.getMovieId(), KEY) }
    }
}