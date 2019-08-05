package com.example.koin.fragment

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.lifecycle.Observer

import com.example.koin.R
import com.example.koin.adapter.CoverAdapter
import com.example.koin.helper.SharedPrefHelper
import com.example.koin.viewmodel.SearchedViewModel
import kotlinx.android.synthetic.main.movie_list.*
import kotlinx.android.synthetic.main.searched_fragment.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SearchFragment : Fragment() {

    private val searchedViewModel: SearchedViewModel by sharedViewModel()
    private val sharedHelper: SharedPrefHelper by inject()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.searched_fragment, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchDone.setOnClickListener {
            if (isValid()){
                sharedHelper.saveMovieName(editMovieName.text.toString())
                populateUI()
            }else{
                return@setOnClickListener
            }
        }
    }

    private fun isValid(): Boolean{
        return if(!TextUtils.isEmpty(editMovieName.text.toString().trim()) && editMovieName.text.toString().length > 2 ) true else false
    }
    private fun populateUI(){
        searchedViewModel.loadingMovies().observe(this, Observer {
            search_recycle_view.adapter = CoverAdapter(activity as Context, it.movieList)
            search_recycle_view.setHasFixedSize(true)
        })
    }


}
