package com.example.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.core.domain.model.Favorite
import com.example.core.utils.GET_IMAGE
import com.example.core.utils.loadImage
import com.example.favorite.databinding.ItemFavoriteBinding

class FavoriteAdapter: RecyclerView.Adapter<FavoriteAdapter.DataViewHolder>() {

    private lateinit var favorites: List<Favorite>

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setItems(favorites: List<Favorite>) {
        this.favorites = favorites
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(
            ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val favorite = favorites[position]
        holder.bind(favorite)
    }

    override fun getItemCount(): Int = favorites.size

    inner class DataViewHolder(private val binding: ItemFavoriteBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(favorite: Favorite) {
            val getImage = String.format("%s%s", GET_IMAGE, favorite.posterPath)
            binding.imgMovie.loadImage(getImage)
            binding.imgMovie.setOnClickListener {
                onItemClickCallback.detail(favorite.id, favorite.catalogueType)
            }
        }
    }

    interface OnItemClickCallback {
        fun detail(id: Int, catalogueType: Int)
    }
}