package com.example.koin.viewmodel

import androidx.lifecycle.LiveData
import com.example.koin.network_data.MovieList
import com.example.koin.repository.PopularRepository

class PopularViewModel(val popularRepository: PopularRepository): BaseViewModel<MovieList>() {

    override fun loadingMovies(): LiveData<MovieList> {
        return popularRepository.loadMovies()
    }
}