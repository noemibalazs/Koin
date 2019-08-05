package com.example.koin.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.example.koin.R
import com.example.koin.adapter.CoverAdapter
import com.example.koin.viewmodel.VotedViewModel
import kotlinx.android.synthetic.main.movie_list.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class VotedFragment : Fragment() {

    private val votedViewModel: VotedViewModel by sharedViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.movie_list, container, false)
        populateUI()
        return view
    }

    private fun populateUI(){
        votedViewModel.loadingMovies().observe(this, Observer {
            cover_recycle_view.adapter = CoverAdapter(activity as Context, it.movieList)
            cover_recycle_view.setHasFixedSize(true)
        })
    }
}
