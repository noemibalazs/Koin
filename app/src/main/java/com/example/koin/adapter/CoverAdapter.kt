package com.example.koin.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.koin.R
import com.example.koin.helper.SharedPrefHelper
import com.example.koin.network_data.Movie
import com.example.koin.ui.MovieDetailsActivity
import com.example.koin.util.MOVIE
import com.example.koin.util.getMoviePoster
import org.koin.core.KoinComponent
import org.koin.core.inject


class CoverAdapter(context: Context, val myList: List<Movie>) : ArrayAdapter<Movie>(context, 0, myList), KoinComponent {

    private val sharedPrefHelper: SharedPrefHelper by inject()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.movie_cover, parent, false)
        }
        val image = view?.findViewById<ImageView>(R.id.favorite_image)
        val movie = myList[position]

        image?.let {
            Glide.with(context)
                .load(getMoviePoster(movie.posterPath))
                .error(R.drawable.miss_sloane)
                .placeholder(R.drawable.miss_sloane)
                .into(it)

            it.setOnClickListener {
                sharedPrefHelper.saveMovieId(movie.id)
                openMovieDetails(movie)
            }
        }
        return view!!
    }

    private fun openMovieDetails(movie: Movie) {
        val intent = Intent(context, MovieDetailsActivity::class.java)
        intent.apply {
            putExtra(MOVIE, movie)
        }
        context.startActivity(intent)
    }
}