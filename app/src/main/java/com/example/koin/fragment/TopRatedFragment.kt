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
import com.example.koin.adapter.CoverAdapter
import com.example.koin.viewmodel.TopRatedViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class TopRatedFragment : Fragment() {

    private val topRatedViewModel: TopRatedViewModel by sharedViewModel()
    private lateinit var gridView: GridView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.movie_list, container, false)

        gridView = view.findViewById(R.id.list_grid_view)
        getTopRatedList()

        return view
    }

    private fun getTopRatedList(){
        topRatedViewModel.loadingMovies().observe(this, Observer {
            gridView.adapter = CoverAdapter(activity as Context, it.movieList)
        })
    }
}