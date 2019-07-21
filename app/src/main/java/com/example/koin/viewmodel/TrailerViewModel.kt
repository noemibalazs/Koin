package com.example.koin.viewmodel

import androidx.lifecycle.LiveData
import com.example.koin.network_data.TrailerList
import com.example.koin.repository.TrailerRepository

class TrailerViewModel(val trailerRepository: TrailerRepository): BaseViewModel<TrailerList>() {
    override fun loadingMovies(): LiveData<TrailerList> {
        return trailerRepository.loadMovies()
    }
}