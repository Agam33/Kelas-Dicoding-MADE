package com.example.myapplication.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.core.domain.model.Favorite
import com.example.core.domain.model.Tv
import com.example.core.utils.GET_IMAGE
import com.example.core.utils.Resource
import com.example.core.utils.getColorFloatingButton
import com.example.core.utils.loadImage
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityDetailTvShowBinding
import com.example.myapplication.ui.tvshow.TvShowFragment.Companion.TV_SHOW_ID
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailTvShowActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailTvShowBinding

    private val detailViewModel: DetailViewModel by viewModel()

    private lateinit var favorite: Favorite

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTvShowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.detail)

        val id = intent.getIntExtra(TV_SHOW_ID, 0 )
        detailViewModel.isFavorite(id).observe(this@DetailTvShowActivity) { isFavorite ->
            setFavorite(isFavorite.isFavorite)
        }
        detailViewModel.getTvById(id).observe(this) { tvShow ->
            when (tvShow) {
                is Resource.Success -> {
                    binding.topLayout.visibility = View.VISIBLE
                    binding.btmLayout.visibility = View.VISIBLE
                    setupUI(tvShow.data)
                }
                is Resource.Loading -> {
                    binding.topLayout.visibility = View.GONE
                    binding.btmLayout.visibility = View.GONE
                }
                is Resource.Error -> {
                    Toast.makeText(
                        this@DetailTvShowActivity,
                        getString(R.string.txt_no_connetion) + " -> " + tvShow.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun setupUI(data: Tv?) = with(binding) {

        if(data != null) {
            val popularity = data.popularity.toInt().toString()
            val episodes = data.totalEpisode.toString()
            val userScore = data.voteAverage * 10
            val getImg = String.format(getString(R.string.get_image), GET_IMAGE, data.imgUrl)

            imgMovie.loadImage(getImg)
            tvTitleMovie.text = data.name
            tvPopularity.text = popularity
            tvEpisode.text = episodes
            tvUserScore.text = String.format(getString(R.string.user_score), userScore.toInt(), "%")
            tvOverview.text = data.overview

            favorite = Favorite(data.id, data.imgUrl,true, data.catalogueType.ordinal)
        }
    }

    private fun setFavorite(isFavorite: Boolean) {
        binding.fabFavorite.imageTintList = getColorFloatingButton(isFavorite)
        binding.fabFavorite.setOnClickListener {
            if(isFavorite) {
                detailViewModel.deleteFavorite(favorite)
            } else {
                detailViewModel.setFavorite(favorite)
            }
        }
    }
}