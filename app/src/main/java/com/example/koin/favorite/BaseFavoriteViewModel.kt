package com.example.koin.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.koin.room.MovieDao
import com.example.koin.room.MovieDataBase
import com.example.koin.room.MovieEntity

abstract class BaseFavoriteViewModel(application: Application, movieDao: MovieDao) : AndroidViewModel(application) {
    abstract fun favoriteMovies(): LiveData<List<MovieEntity>>
}