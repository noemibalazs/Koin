package com.example.koin.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.koin.network_data.Movie
import com.example.koin.room.MovieDao
import com.example.koin.room.MovieEntity

abstract class BaseFavoriteViewModel(application: Application, internal val movieDao: MovieDao) : AndroidViewModel(application) {
    abstract fun favoriteMovies(): LiveData<List<MovieEntity>>

    fun getMovies(): LiveData<List<MovieEntity>>{
        return movieDao.getMovieList()
    }
}