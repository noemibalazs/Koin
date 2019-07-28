package com.example.koin.ui

import android.content.Intent
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
import org.koin.android.ext.android.inject

class MovieDetailsActivity : AppCompatActivity() {

    private val sharedHelper: SharedPrefHelper by inject()
    private val movieDao: MovieDao by inject()
    private lateinit var entity: MovieEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie)

        val entityObserver = object: Observer<MovieEntity>{
            override fun onChanged(movieEntity: MovieEntity?) {
                movieEntity?.let {
                    entity = it
                    populateUI(it)
                }
            }
        }
        movieDao.getMovieByID(sharedHelper.getMovieId()).observe(this, entityObserver)
    }

    private fun populateUI(entity: MovieEntity){
        tv_title.text = entity.title
        tv_detail_description.text = entity.description
        tv_detail_release_date.text = entity.releaseDate
        tv_detail_user_rating.text = String.format(getString(R.string.rating_builder), entity.rating)
        loadPicture(entity.posterPath, iv_detail_image)
        populateReviews(entity)
        populateTrailers(entity)
    }

    private fun populateReviews(entity: MovieEntity){
        entity.reviewList?.let {
            recycle_review.setHasFixedSize(true)
            recycle_review.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
            recycle_review.adapter = ReviewAdapter(this, it.reviewList)
        }
    }

    private fun populateTrailers(entity: MovieEntity){
        entity.trailerList?.let {
            recycle_trailer.setHasFixedSize(true)
            recycle_trailer.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
            recycle_trailer.adapter = TrailerAdapter(this, it.trailerList)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.shared, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.shared_movie){
            shareMovieDetails(entity)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun shareMovieDetails(entity: MovieEntity){
        val title = String.format(getString(R.string.shared_movie_title), entity.title)
        val subject = String.format(getString(R.string.shared_movie_message), entity.description, entity.rating, entity.releaseDate)

        val intent = Intent(Intent.ACTION_SEND)
        intent.apply {
            type = "plain/text"
            putExtra(Intent.EXTRA_TEXT, subject )
            putExtra(Intent.EXTRA_SUBJECT, title)
        }
        startActivity(Intent.createChooser(intent, getString(R.string.shared_choice)))
    }

}
