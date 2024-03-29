package com.example.koin.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.koin.R
import com.example.koin.network_data.Movie
import com.example.koin.room.MovieEntity

fun getMoviePoster(posterPath: String): String{
    return POSTER_URL + posterPath
}

fun getMovieYoutubePath(key: String):String{
    return YOUTUBE_PATH + key
}

fun getYoutubeScreenShot(path:String):String{
    return YOUTUBE_START + path + YOUTUBE_END
}

fun Context.loadPicture(link:String, view: ImageView) {
    Glide.with(this)
        .load(link)
        .placeholder(R.drawable.miss_sloane)
        .error(R.drawable.miss_sloane)
        .into(view)
}

fun Context.getDrawableUri(): String{
    val path = Uri.parse("android.resource://" + this.packageName + "drawable/miss_sloane")
    return path.toString()
}

fun <T> Context.openActivity(dest: Class<T>){
    val  intent = Intent(this, dest)
    this.startActivity(intent)
}

fun Context.movie2Entity(movie: Movie): MovieEntity{
    if (movie.posterPath == null){
        return MovieEntity(movie.id, movie.title, movie.description, movie.releaseDate, movie.rating, this.getDrawableUri())
    }else{
        return MovieEntity(movie.id, movie.title, movie.description, movie.releaseDate, movie.rating, getMoviePoster(movie.posterPath))
    }
}
