package com.example.koin.viewmodel

import androidx.lifecycle.LiveData
import com.example.koin.network_data.MovieList
import com.example.koin.repository.TopRatedRepository

class TopRatedViewModel(val topRatedRepository: TopRatedRepository): BaseViewModel<MovieList>() {

    override fun loadingMovies(): LiveData<MovieList> {
       return topRatedRepository.loadMovies()
    }
}