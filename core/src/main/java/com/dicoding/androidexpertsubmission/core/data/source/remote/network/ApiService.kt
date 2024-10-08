package com.dicoding.androidexpertsubmission.core.data.source.remote.network

import com.dicoding.androidexpertsubmission.core.data.source.remote.reponse.GamesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("games")
    suspend fun getList(
        @Query("key") apiKey: String
    ): GamesResponse
}