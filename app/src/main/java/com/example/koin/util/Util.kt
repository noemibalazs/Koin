package com.example.koin.util

fun getMoviePoster(posterPath: String): String{
    return POSTER_URL + posterPath
}

fun getMovieYoutubePath(key: String):String{
    return YOUTUBE_PATH + key
}