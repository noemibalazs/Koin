package com.example.koin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
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

class FavoriteAdapter(context: Context, val myList: MutableList<MovieEntity>) : ArrayAdapter<MovieEntity>(context, 0, myList), KoinComponent{

    private val sharedPrefHelper: SharedPrefHelper by inject()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.movie_cover, parent, false)
        }
        val image = view?.findViewById<ImageView>(R.id.favorite_image)
        val entity = myList[position]

        context.loadPicture(entity.posterPath, image!!)

        image.setOnClickListener {
            sharedPrefHelper.saveMovieId(entity.id)
            context.openActivity(FavoriteDetailsActivity::class.java)
        }

        return view!!
    }
}