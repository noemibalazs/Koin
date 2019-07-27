package com.example.koin.viewmodel

import androidx.lifecycle.LiveData
import com.example.koin.network_data.MovieList
import com.example.koin.repository.SearchedRepository

class SearchedViewModel(val searchedRepository: SearchedRepository): BaseViewModel<MovieList>() {
    override fun loadingMovies(): LiveData<MovieList> {
        return searchedRepository.loadMovies()
    }
}