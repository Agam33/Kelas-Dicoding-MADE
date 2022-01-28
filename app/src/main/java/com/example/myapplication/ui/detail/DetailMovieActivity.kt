package com.example.myapplication.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.core.domain.model.Favorite
import com.example.core.domain.model.Movie
import com.example.core.utils.GET_IMAGE
import com.example.core.utils.Resource
import com.example.core.utils.getColorFloatingButton
import com.example.core.utils.loadImage
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityDetailMovieBinding
import com.example.myapplication.ui.movie.MovieFragment.Companion.MOVIE_ID
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailMovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailMovieBinding

    private val detailViewModel: DetailViewModel by viewModel()

    private lateinit var favorite: Favorite

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.detail)

        val id = intent.getIntExtra(MOVIE_ID, 0)
        detailViewModel.isFavorite(id).observe(this@DetailMovieActivity) { isFavorite ->
            setFavorite(isFavorite.isFavorite)
        }
        detailViewModel.getMovieById(id).observe(this) { movie ->
            when (movie) {
                is Resource.Success -> {
                    binding.topLayout.visibility = View.VISIBLE
                    binding.btmLayout.visibility = View.VISIBLE
                    setupUI(movie.data)
                }

                is Resource.Loading -> {
                    binding.topLayout.visibility = View.GONE
                    binding.btmLayout.visibility = View.GONE
                }
                is Resource.Error -> {
                    Toast.makeText(
                        this@DetailMovieActivity,
                        getString(R.string.txt_no_connetion) + " -> " + movie.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun setupUI(data: Movie?) = with(binding) {
        if(data != null) {
            val hours = data.runtime / 60
            val minutes = data.runtime % 60
            val vote = data.voteAverage * 10
            val getImg = String.format(getString(R.string.get_image), GET_IMAGE, data.imgUrl)

            popularity.text = data.popularity.toInt().toString()
            tvTitleMovie.text = data.name
            tvLanguage.text = data.language
            tvDuration.text = String.format(getString(R.string.movie_duration), hours, minutes)
            imgMovie.loadImage(getImg)
            tvReleaseDate.text = data.releaseDate
            tvUserScore.text = String.format(getString(R.string.user_score), vote.toInt(),"%")
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