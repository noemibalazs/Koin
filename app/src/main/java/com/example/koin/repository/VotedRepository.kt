package com.example.koin.repository

import androidx.lifecycle.LiveData
import com.example.koin.helper.SharedPrefHelper
import com.example.koin.network.MovieService
import com.example.koin.network_data.MovieList
import com.example.koin.util.KEY
import com.example.koin.util.VOTED_QUERY

class VotedRepository(movieService: MovieService, sharedPrefHelper: SharedPrefHelper): BaseRepository<MovieList>(movieService, sharedPrefHelper) {

    override fun loadMovies(): LiveData<MovieList> {
        return fetchData {  service.getVotedMovies(KEY, VOTED_QUERY) }
    }
}