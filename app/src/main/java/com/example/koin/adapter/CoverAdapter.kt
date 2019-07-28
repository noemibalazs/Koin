package com.example.koin.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.koin.R
import com.example.koin.helper.SharedPrefHelper
import com.example.koin.network_data.Movie
import com.example.koin.network_data.ReviewList
import com.example.koin.network_data.TrailerList
import com.example.koin.room.MovieDao
import com.example.koin.room.MovieEntity
import com.example.koin.ui.MovieDetailsActivity
import com.example.koin.util.*
import com.example.koin.viewmodel.ReviewViewModel
import com.example.koin.viewmodel.TrailerViewModel
import org.jetbrains.anko.doAsync
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.logger.KOIN_TAG

class CoverAdapter(context: Context, val myList: List<Movie>) : ArrayAdapter<Movie>(context, 0, myList), KoinComponent {

    private val sharedPrefHelper: SharedPrefHelper by inject()
    private val movieDao: MovieDao by inject()
    private val trailerViewModel: TrailerViewModel by inject()
    private val reviewViewModel: ReviewViewModel by inject()

    private lateinit var movieEntity: MovieEntity

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var view = convertView
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.movie_cover, parent, false)
        }
        val image = view?.findViewById<ImageView>(R.id.favorite_image)
        val movie = myList[position]

        if (movie.posterPath == null){
            context.loadPicture(context.getDrawableUri(), image!!)
        }else{
            context.loadPicture(getMoviePoster(movie.posterPath), image!!)
        }

        movieEntity = context.movie2Entity(movie)

        image.let {
            it.setOnClickListener {

                sharedPrefHelper.saveMovieId(movieEntity.id)
                saveMovie(movieEntity)

                context.openActivity(MovieDetailsActivity::class.java)
            }
        }
        return view!!
    }

    private fun saveMovie(movie: MovieEntity) {
        checkReviews()
        checkTrailers()
        doAsync {
            movieDao.addMovie2DB(movie)
        }
    }

    private fun checkTrailers() {
        val trailerObserver = object : Observer<TrailerList> {
            override fun onChanged(trailers: TrailerList?) {
                trailers?.let {
                    movieEntity.trailerList = it
                    saveMovie(movieEntity)
                }
            }
        }
        trailerViewModel.loadingMovies().observe(context as LifecycleOwner, trailerObserver)
    }

    private fun checkReviews(){
        val reviewObserver = object : Observer<ReviewList>{
            override fun onChanged(reviews: ReviewList?) {
                reviews?.let {
                   movieEntity.reviewList = it
                    saveMovie(movieEntity)
                }
            }
        }
        reviewViewModel.loadingMovies().observe(context as LifecycleOwner,reviewObserver)
    }

}