package com.example.koin.favorite

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.koin.network_data.Movie
import com.example.koin.room.MovieDao

class FavoriteViewModel(application: Application, movieDao: MovieDao) : BaseFavoriteViewModel(application, movieDao) {
    override fun favoriteMovies(): LiveData<List<Movie>> {
        return getMovies()
    }

}