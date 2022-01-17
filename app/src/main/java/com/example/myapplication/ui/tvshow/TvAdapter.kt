package com.example.myapplication.ui.tvshow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.core.domain.model.CatalogueResult
import com.example.core.utils.GET_IMAGE
import com.example.core.utils.loadImage
import com.example.myapplication.databinding.ItemTvShowBinding

class TvAdapter: RecyclerView.Adapter<TvAdapter.DataViewHolder>() {

    private lateinit var tvShow: List<CatalogueResult>

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun addList(tvShow: List<CatalogueResult>) {
        this.tvShow = tvShow
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(
            ItemTvShowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val tvShow = tvShow[position]
        holder.bind(tvShow)
    }

    override fun getItemCount(): Int = tvShow.size

    inner class DataViewHolder(
        private val item: ItemTvShowBinding,
    ): RecyclerView.ViewHolder(item.root) {

        fun bind(catalogueResult: CatalogueResult) {
            item.imgTv.loadImage("${GET_IMAGE}${catalogueResult.posterPath}")
            item.imgTv.setOnClickListener {
                onItemClickCallback.detailTv(catalogueResult.id)
            }
        }
    }

    interface OnItemClickCallback {
        fun detailTv(id: Int)
    }
}