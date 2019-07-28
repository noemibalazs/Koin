package com.example.koin.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.lifecycle.Observer
import com.example.koin.R
import com.example.koin.adapter.FavoriteAdapter
import com.example.koin.favorite.FavoriteViewModel
import org.koin.android.ext.android.inject

class FavoriteFragment : Fragment() {

    private val favoriteViewModel:FavoriteViewModel by inject()
    private lateinit var gridView: GridView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.movie_list, container, false)

        gridView = view.findViewById(R.id.list_grid_view)
        populateUI()

        return view
    }

    private fun populateUI(){

        favoriteViewModel.getMovies().observe(this, Observer {
            gridView.adapter = FavoriteAdapter(activity as Context, it)
        })
    }
}
