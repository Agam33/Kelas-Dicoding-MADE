package com.example.myapplication.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapplication.ui.movie.MovieFragment
import com.example.myapplication.ui.tvshow.TvShowFragment

class SectionsPagerAdapter(
    activity: AppCompatActivity
): FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position) {
            0 -> fragment = MovieFragment()
            1 -> fragment = TvShowFragment()
            else -> {}
        }
        return fragment as Fragment
    }
}