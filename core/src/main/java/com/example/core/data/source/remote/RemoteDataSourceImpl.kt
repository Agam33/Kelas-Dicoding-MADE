package com.example.core.data.source.remote

import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.response.ResultResponse
import com.example.core.data.source.remote.response.DetailMovieResponse
import com.example.core.data.source.remote.response.DetailTvResponse
import kotlinx.coroutines.flow.Flow

interface RemoteDataSourceImpl {
/*
    Movie
 */
    suspend fun getListMovieBy(type: String): Flow<ApiResponse<List<ResultResponse>>>
    suspend fun getDetailMovie(id: Int): Flow<ApiResponse<DetailMovieResponse>>
/*
    Tv Show
 */
    suspend fun getListTvBy(type: String): Flow<ApiResponse<List<ResultResponse>>>
    suspend fun getDetailTv(id: Int): Flow<ApiResponse<DetailTvResponse>>
}