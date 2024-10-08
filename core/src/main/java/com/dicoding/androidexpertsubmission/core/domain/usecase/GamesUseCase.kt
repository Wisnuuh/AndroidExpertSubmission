package com.dicoding.androidexpertsubmission.core.domain.usecase

import com.dicoding.androidexpertsubmission.core.data.Resource
import com.dicoding.androidexpertsubmission.core.domain.model.Games
import kotlinx.coroutines.flow.Flow

interface GamesUseCase {

    fun getAllGames(): Flow<Resource<List<Games>>>
    fun getFavoriteGames(): Flow<List<Games>>
    fun setFavoriteGames(games: Games, state: Boolean)
}