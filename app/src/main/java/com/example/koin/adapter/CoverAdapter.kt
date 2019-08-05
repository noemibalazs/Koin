package com.example.koin.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
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

class CoverAdapter(val context: Context, val myList: MutableList<Movie>): RecyclerView.Adapter<CoverAdapter.CustomViewHolder>(), KoinComponent {

    private val sharedPrefHelper: SharedPrefHelper by inject()
    private val movieDao: MovieDao by inject()
    private val trailerViewModel: TrailerViewModel by inject()
    private val reviewViewModel: ReviewViewModel by inject()

    private lateinit var movieEntity: MovieEntity

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.movie_cover, parent, false)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return myList.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val movie = myList[position]

        if (movie.posterPath == null) {
            context.loadPicture(context.getDrawableUri(), holder.imageHolder)
        } else {
            context.loadPicture(getMoviePoster(movie.posterPath), holder.imageHolder)
        }

        holder.imageHolder.setOnClickListener {

            movieEntity = context.movie2Entity(movie)

            sharedPrefHelper.saveMovieId(movieEntity.id)

            checkTrailers()
            checkReviews()

            context.openActivity(MovieDetailsActivity::class.java)
            Log.d(KOIN_TAG, "My movie entity is: ${movieEntity.title}")

        }
    }


    inner class CustomViewHolder(view: View): RecyclerView.ViewHolder(view){
        val imageHolder = view.findViewById<ImageView>(R.id.favorite_image)
    }

    private fun saveMovie(movie: MovieEntity) {
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
                    Log.d(KOIN_TAG, "Trailer list size: ${it.trailerList.size}")
                }
            }
        }
        trailerViewModel.loadingMovies().observe(context as LifecycleOwner, trailerObserver)
    }

    private fun checkReviews() {
        val reviewObserver = object : Observer<ReviewList> {
            override fun onChanged(reviews: ReviewList?) {
                reviews?.let {
                    movieEntity.reviewList = it
                    saveMovie(movieEntity)
                    Log.d(KOIN_TAG, "Review list size: ${it.reviewList.size}")
                }
            }
        }
        reviewViewModel.loadingMovies().observe(context as LifecycleOwner, reviewObserver)
    }
}