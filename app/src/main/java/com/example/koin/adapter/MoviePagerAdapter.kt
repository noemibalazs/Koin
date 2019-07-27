package com.example.koin.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.koin.R
import com.example.koin.fragment.*
import com.example.koin.util.ONE
import com.example.koin.util.THREE
import com.example.koin.util.TWO
import com.example.koin.util.ZERO

class MoviePagerAdapter(val context: Context, manager: FragmentManager) :
    FragmentStatePagerAdapter(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int {
        return 5
    }

    override fun getItem(position: Int): Fragment {
        when (position) {
            ZERO -> {
                return TopRatedFragment()
            }
            ONE -> {
                return PopularFragment()
            }
            TWO -> {
                return VotedFragment()
            }

            THREE -> {
                return SearchFragment()
            }

            else -> {
                return FavoriteFragment()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            ZERO -> {
                return context.getString(R.string.fragment_top_rated)
            }
            ONE -> {
                return context.getString(R.string.fragment_popular)
            }

            TWO -> {
                return context.getString(R.string.fragment_voted)
            }

            THREE -> {
                return context.getString(R.string.fragment_searched)
            }

            else -> {
                return context.getString(R.string.fragment_favorite)
            }
        }
    }
}