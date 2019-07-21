package com.example.koin.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.koin.R
import com.example.koin.adapter.PagerAdapter
import com.example.koin.viewmodel.TopRatedViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val topRatedViewModel: TopRatedViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_view_pager.adapter = PagerAdapter(this, supportFragmentManager)
        main_tab_layout.setupWithViewPager(main_view_pager)
    }
}
