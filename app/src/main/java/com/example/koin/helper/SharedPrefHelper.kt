package com.example.koin.helper

import android.content.Context
import com.example.koin.util.MOVIE_ID
import com.example.koin.util.MOVIE_NAME

class SharedPrefHelper(context: Context) {
    private val shared = context.getSharedPreferences("My pref", Context.MODE_PRIVATE)

    fun saveMovieId(id: Int){
        val edit = shared.edit()
        edit.putInt(MOVIE_ID, id)
        edit.apply()
    }

    fun getMovieId():Int{
        return shared.getInt(MOVIE_ID, 0)
    }

    fun saveMovieName(name: String){
        val edit = shared.edit()
        edit.putString(MOVIE_NAME, name)
        edit.apply()
    }

    fun getMovieName(): String{
        return shared.getString(MOVIE_NAME, "")
    }
}