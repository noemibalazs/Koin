package com.example.koin.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.koin.network_data.Movie
import com.example.koin.room.MovieDao

abstract class BaseFavoriteViewModel(application: Application, internal val movieDao: MovieDao) : AndroidViewModel(application) {
    abstract fun favoriteMovies(): LiveData<List<Movie>>

    fun getMovies(): LiveData<List<Movie>>{
        return movieDao.getMovieList()
    }
}