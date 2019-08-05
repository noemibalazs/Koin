package com.example.koin.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.koin.R
import com.example.koin.adapter.ReviewAdapter
import com.example.koin.adapter.TrailerAdapter
import com.example.koin.helper.SharedPrefHelper
import com.example.koin.room.MovieDao
import com.example.koin.room.MovieEntity
import com.example.koin.util.loadPicture
import kotlinx.android.synthetic.main.movie.*
import kotlinx.android.synthetic.main.nested_detail.*
import org.jetbrains.anko.doAsync
import org.koin.android.ext.android.inject


class FavoriteDetailsActivity : AppCompatActivity() {

    private val movieDAO: MovieDao by inject()
    private val sharedPref: SharedPrefHelper by inject()

    private lateinit var entity: MovieEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie)

        val movieObserver = object : Observer<MovieEntity> {
            override fun onChanged(movieEntity: MovieEntity?) {
                movieEntity?.let {
                    entity = it
                    populateUI(it)
                }
            }
        }

        movieDAO.getMovieByID(sharedPref.getMovieId()).observe(this, movieObserver)

    }

    private fun populateUI(entity: MovieEntity) {
        tv_title.text = entity.title
        tv_detail_description.text = entity.description
        tv_detail_user_rating.text = String.format(getString(R.string.rating_builder), entity.rating)
        tv_detail_release_date.text = entity.releaseDate
        loadPicture(entity.posterPath, iv_detail_image)
        populateReviews(entity)
        populateTrailers(entity)
    }

    private fun populateReviews(entity: MovieEntity){
        recycle_review.setHasFixedSize(true)
        recycle_review.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        entity.reviewList?.let {
            recycle_review.adapter = ReviewAdapter(this, it.reviewList)
        }
    }

    private fun populateTrailers(entity: MovieEntity){
        recycle_trailer.setHasFixedSize(true)
        recycle_trailer.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        entity.trailerList?.let {
            recycle_trailer.adapter = TrailerAdapter(this, it.trailerList)
        }
    }

    private fun deleteMovie(entity: MovieEntity){
        doAsync {
            movieDAO.deleteMovieFromDB(entity)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.delete, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete_movie){
            deleteMovie(entity)
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
