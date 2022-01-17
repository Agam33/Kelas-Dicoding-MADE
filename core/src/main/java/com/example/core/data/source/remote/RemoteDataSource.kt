package com.example.core.data.source.remote

import android.content.ContentValues.TAG
import android.util.Log
import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.network.ApiService
import com.example.core.data.source.remote.response.DetailMovieResponse
import com.example.core.data.source.remote.response.DetailTvResponse
import com.example.core.data.source.remote.response.ResultResponse
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception

class RemoteDataSource(
    private val apiService: ApiService
) : RemoteDataSourceImpl{

    override suspend fun getListMovieBy(type: String): Flow<ApiResponse<List<ResultResponse>>> =
        flow {
            try {
                val response = apiService.getListMovieBy(type)
                val result = response.result
                if(result.isNotEmpty()) {
                    emit(ApiResponse.Success(result))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(IO)

    override suspend fun getDetailMovie(id: Int): Flow<ApiResponse<DetailMovieResponse>> =
        flow {
            try {
                val response = apiService.getDetailMovie(id)
                Log.d(TAG, "getDetailMovie: $response")
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(IO)

    override suspend fun getListTvBy(type: String): Flow<ApiResponse<List<ResultResponse>>> =
        flow {
            try {
                val response = apiService.getListTvShowBy(type)
                val result = response.result
                if(result.isNotEmpty()) {
                    emit(ApiResponse.Success(result))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(IO)

    override suspend fun getDetailTv(id: Int): Flow<ApiResponse<DetailTvResponse>> =
        flow {
            try {
                val response = apiService.getDetailTv(id)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(IO)
}