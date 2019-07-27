package com.example.koin.viewmodel

import androidx.lifecycle.LiveData
import com.example.koin.network_data.MovieList
import com.example.koin.repository.VotedRepository

class VotedViewModel(val votedRepository: VotedRepository): BaseViewModel<MovieList>() {
    override fun loadingMovies(): LiveData<MovieList> {
        return votedRepository.loadMovies()
    }
}