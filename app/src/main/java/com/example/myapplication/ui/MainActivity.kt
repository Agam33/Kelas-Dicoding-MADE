package com.example.myapplication.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
    }

    private fun setupUI() {
        binding.apply {
            val sectionsPagerAdapter = SectionsPagerAdapter(this@MainActivity)
            viewPagerMainActivity.adapter = sectionsPagerAdapter
            TabLayoutMediator(tabsMainActivity, viewPagerMainActivity) {tab, position ->
                tab.text = when(position) {
                    TAB_MOVIE_FRAGMENT -> getString(R.string.txt_movies)
                    TAB_TV_SHOW_FRAGMENT -> getString(R.string.txt_tv_shows)
                    else -> ""
                }
            }.attach()
            viewPagerMainActivity.isUserInputEnabled = false
        }

        binding.fabFavorite.setOnClickListener {
            startActivity(Intent(this@MainActivity, Class.forName("com.example.favorite.FavoriteActivity")))
        }
    }

    companion object {
        private const val TAB_MOVIE_FRAGMENT = 0
        private const val TAB_TV_SHOW_FRAGMENT = 1
    }
}