package com.example.koin.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.koin.R
import com.example.koin.adapter.FavoriteAdapter
import com.example.koin.favorite.FavoriteViewModel
import kotlinx.android.synthetic.main.movie_list.*
import org.koin.android.ext.android.inject

class FavoriteFragment : Fragment() {

    private val favoriteViewModel:FavoriteViewModel by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.movie_list, container, false)
        populateUI()

        return view
    }

    private fun populateUI(){
        favoriteViewModel.getMovies().observe(this, Observer {
            cover_recycle_view.adapter = FavoriteAdapter(activity as Context, it)
            cover_recycle_view.setHasFixedSize(true)
        })
    }
}
