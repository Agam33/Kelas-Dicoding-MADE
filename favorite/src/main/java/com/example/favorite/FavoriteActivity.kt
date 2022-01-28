package com.example.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import androidx.recyclerview.widget.GridLayoutManager
import com.example.core.domain.model.Favorite
import com.example.core.utils.CatalogueType
import com.example.favorite.databinding.ActivityFavoriteBinding
import com.example.favorite.di.favoriteModule
import com.example.myapplication.ui.detail.DetailMovieActivity
import com.example.myapplication.ui.detail.DetailTvShowActivity
import com.example.myapplication.ui.movie.MovieFragment.Companion.MOVIE_ID
import com.example.myapplication.ui.tvshow.TvShowFragment.Companion.TV_SHOW_ID
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class FavoriteActivity : AppCompatActivity(), FavoriteAdapter.OnItemClickCallback {

    private lateinit var binding: ActivityFavoriteBinding

    private val favoriteViewModel: FavoriteViewModel by viewModel()

    private var  spanCount: Int = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favoriteModule)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = getString(R.string.title_appbar)
        supportActionBar?.elevation = 4f

        spanCount = when(resources.configuration.orientation) {
            LANDSCAPE_MODE -> 4
            POTRAIT_MODE -> 2
            else -> {2}
        }

        favoriteViewModel.favorites.observe(this) {
            if (it.isEmpty()) {
                binding.tvNoFavorites.visibility = View.VISIBLE
                binding.rvFavorite.visibility = View.GONE
            } else {
                showFavorites(it)
                binding.tvNoFavorites.visibility = View.GONE
                binding.rvFavorite.visibility = View.VISIBLE
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_favorite, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_filter_list -> {
                showFilterMenu()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showFilterMenu() {
        val view = findViewById<View>(R.id.action_filter_list) ?: return
        PopupMenu(this, view).run {
            menuInflater.inflate(R.menu.filter_favorite, menu)

            setOnMenuItemClickListener {
                favoriteViewModel.filter(
                    when(it.itemId) {
                        R.id.action_tv_show -> CatalogueType.TV
                        R.id.action_movie -> CatalogueType.MOVIE
                        else -> CatalogueType.ALL
                    }
                )
                true
            }
            show()
        }
    }

    private fun showFavorites(favorites: List<Favorite>) {
        val favoriteAdapter = FavoriteAdapter()
        favoriteAdapter.setItems(favorites)
        favoriteAdapter.setOnItemClickCallback(this)
        binding.rvFavorite.let {
            it.adapter = favoriteAdapter
            it.layoutManager = GridLayoutManager(this@FavoriteActivity, spanCount)
            it.setHasFixedSize(true)
        }
    }

    override fun detail(id: Int, catalogueType: Int) {
        when(catalogueType) {
            MOVIE -> {
                startActivity(Intent(this@FavoriteActivity, DetailMovieActivity::class.java).apply {
                    putExtra(MOVIE_ID, id)
                })
            }
            TV -> {
                startActivity(Intent(this@FavoriteActivity, DetailTvShowActivity::class.java).apply {
                    putExtra(TV_SHOW_ID, id)
                })
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(favoriteModule)
    }

    companion object {
        private const val LANDSCAPE_MODE: Int = 2
        private const val POTRAIT_MODE: Int = 1
        private const val MOVIE = 0
        private const val TV = 1
    }
}