package com.example.myapplication.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.core.domain.model.CatalogueResult
import com.example.core.utils.GET_IMAGE
import com.example.core.utils.loadImage
import com.example.myapplication.databinding.ItemMoviesBinding

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.DataViewHolder>() {

    private lateinit var movies: List<CatalogueResult>

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun addList(movies: List<CatalogueResult>) {
        this.movies = movies
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(
            ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = movies.size

    inner class DataViewHolder(
        private val item: ItemMoviesBinding,
    ): RecyclerView.ViewHolder(item.root) {

        fun bind(catalogueResult: CatalogueResult) {
            item.imgMovie.loadImage("${GET_IMAGE}${catalogueResult.posterPath}")
            item.imgMovie.setOnClickListener {
                onItemClickCallback.detailMovie(catalogueResult.id)
            }
        }
    }

    interface OnItemClickCallback {
        fun detailMovie(id: Int)
    }
}