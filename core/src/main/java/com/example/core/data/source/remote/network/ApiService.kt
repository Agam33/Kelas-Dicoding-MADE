package com.example.core.data.source.remote.network

import com.example.core.data.source.remote.response.CatalogueResponse
import com.example.core.data.source.remote.response.DetailMovieResponse
import com.example.core.data.source.remote.response.DetailTvResponse
import com.example.core.utils.*
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    // Movie
    @GET("movie/{type}?api_key=$API_KEY")
    suspend fun getListMovieBy(
        @Path("type")type: String
    ): CatalogueResponse

    @GET("movie/{id}?api_key=$API_KEY")
    suspend fun getDetailMovie(
        @Path("id") id: Int
    ): DetailMovieResponse

    // Tv Show
    @GET("tv/{type}?api_key=$API_KEY")
    suspend fun getListTvShowBy(
        @Path("type") type: String
    ): CatalogueResponse


    @GET("tv/{id}?api_key=$API_KEY")
    suspend fun getDetailTv(
        @Path("id") id: Int
    ): DetailTvResponse
}