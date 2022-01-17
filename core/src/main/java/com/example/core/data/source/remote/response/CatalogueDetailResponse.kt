package com.example.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DetailMovieResponse(

    @field:SerializedName("id")
    val id: Int = 0,

    @field:SerializedName("title")
    val name: String,

    @field:SerializedName("poster_path")
    val imgUrl: String,

    @field:SerializedName("spoken_languages")
    val language: List<SpokenLanguage>,

    @field:SerializedName("runtime")
    val runtime: Int,

    @field:SerializedName("release_date")
    val releaseDate: String,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("popularity")
    val popularity: Double,

    @field:SerializedName("vote_average")
    val voteAverage: Double,
)

data class DetailTvResponse(

    @field:SerializedName("id")
    val id: Int = 0,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("poster_path")
    val imgUrl: String,

    @field:SerializedName("number_of_episodes")
    val episode: Int,

    @field:SerializedName("spoken_languages")
    val language: List<SpokenLanguage>,

    @field:SerializedName("release_date")
    val releaseDate: String,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("popularity")
    val popularity: Double,

    @field:SerializedName("vote_average")
    val voteAverage: Double,
)

data class SpokenLanguage(
    @field:SerializedName("name")
    val englishName: String
)