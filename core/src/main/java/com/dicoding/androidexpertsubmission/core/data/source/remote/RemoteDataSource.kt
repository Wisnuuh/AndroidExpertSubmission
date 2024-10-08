package com.dicoding.androidexpertsubmission.core.data.source.remote

import android.util.Log
import com.dicoding.androidexpertsubmission.core.BuildConfig
import com.dicoding.androidexpertsubmission.core.data.source.remote.network.ApiResponse
import com.dicoding.androidexpertsubmission.core.data.source.remote.network.ApiService
import com.dicoding.androidexpertsubmission.core.data.source.remote.reponse.ResultsItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    companion object;

    suspend fun getAllGames(): Flow<ApiResponse<List<ResultsItem>>> {

        val apiKey = BuildConfig.API_KEY

        return flow {
            try {
                val response = apiService.getList(apiKey)
                val dataArray = response.results
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}