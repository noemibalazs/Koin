package com.example.koin.fragment

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.GridView
import android.widget.ImageView
import androidx.lifecycle.Observer

import com.example.koin.R
import com.example.koin.adapter.CoverAdapter
import com.example.koin.helper.SharedPrefHelper
import com.example.koin.viewmodel.SearchedViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SearchFragment : Fragment() {

    private val searchedViewModel: SearchedViewModel by sharedViewModel()
    private val sharedHelper: SharedPrefHelper by inject()
    private lateinit var gridView: GridView
    private lateinit var editName: EditText
    private lateinit var done: ImageView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.searched_fragment, container, false)
        gridView = view.findViewById(R.id.list_grid_view)
        editName = view.findViewById(R.id.edit_movie_name)
        done = view.findViewById(R.id.search_done)

        done.setOnClickListener {
            if (isValid()){
                sharedHelper.saveMovieName(editName.text.toString())
                populateUI()
            }else{
                return@setOnClickListener
            }
        }
        return view
    }

    private fun isValid(): Boolean{
        return if(!TextUtils.isEmpty(editName.text.toString().trim()) && editName.text.toString().length >2 ) true else false
    }

    private fun populateUI(){
        searchedViewModel.loadingMovies().observe(this, Observer {
            gridView.adapter = CoverAdapter(activity as Context, it.movieList)
        })
    }


}
