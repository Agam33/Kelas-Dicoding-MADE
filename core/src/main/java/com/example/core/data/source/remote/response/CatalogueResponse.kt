package com.example.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class CatalogueResponse(
    @field:SerializedName("results")
    val result: List<ResultResponse>
)

data class ResultResponse(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("poster_path")
    val posterPath: String,
)

