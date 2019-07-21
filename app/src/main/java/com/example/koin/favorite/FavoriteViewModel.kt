package com.example.koin.favorite

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.koin.room.MovieDao
import com.example.koin.room.MovieDataBase
import com.example.koin.room.MovieEntity

class FavoriteViewModel(application: Application, val movieDao: MovieDao) : BaseFavoriteViewModel(application, movieDao) {
    override fun favoriteMovies(): LiveData<List<MovieEntity>> {
        return movieDao.getMovieList()
    }
}