package com.example.koin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.koin.R
import com.example.koin.helper.SharedPrefHelper
import com.example.koin.room.MovieEntity
import com.example.koin.ui.FavoriteDetailsActivity
import com.example.koin.util.loadPicture
import com.example.koin.util.openActivity
import org.koin.core.KoinComponent
import org.koin.core.inject

class FavoriteAdapter(val context: Context, val myList: MutableList<MovieEntity>): RecyclerView.Adapter<FavoriteAdapter.FavViewHolder>(), KoinComponent {

    private val sharedPrefHelper: SharedPrefHelper by inject()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.movie_cover, parent, false)
        return FavViewHolder(view)
    }

    override fun getItemCount(): Int {
        return myList.size
    }

    override fun onBindViewHolder(holder: FavViewHolder, position: Int) {
        val entity = myList[position]

        context.loadPicture(entity.posterPath, holder.imageHolder)
        holder.imageHolder.setOnClickListener {
            sharedPrefHelper.saveMovieId(entity.id)
            context.openActivity(FavoriteDetailsActivity::class.java)
        }
    }

    inner class FavViewHolder(view:View): RecyclerView.ViewHolder(view){
        val imageHolder = view.findViewById<ImageView>(R.id.favorite_image)
    }
}