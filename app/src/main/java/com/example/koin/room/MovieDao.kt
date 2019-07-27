package com.example.koin.room

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.koin.network_data.Movie
import com.example.koin.util.MOVIE_DB

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie_table")
    fun getMovieList(): LiveData<List<Movie>>

    @Query("SELECT * FROM movie_table WHERE id = :id")
    fun getMovie(id: Int): LiveData<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMovie2DB(entity: Movie)

    @Delete
    fun deleteMovieFromDB(entity: Movie)

    companion object{
        fun getDataBaseInstance(context:Context): MovieDao{
            return Room.databaseBuilder(context, MovieDataBase::class.java, MOVIE_DB).build().movieDao()
        }
    }
}