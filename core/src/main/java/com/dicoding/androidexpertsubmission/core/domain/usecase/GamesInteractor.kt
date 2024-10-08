package com.dicoding.androidexpertsubmission.core.domain.usecase

import com.dicoding.androidexpertsubmission.core.data.Resource
import com.dicoding.androidexpertsubmission.core.domain.model.Games
import com.dicoding.androidexpertsubmission.core.domain.repository.IGamesRepository
import kotlinx.coroutines.flow.Flow

class GamesInteractor(private val gamesRepository: IGamesRepository): GamesUseCase {
    override fun getAllGames(): Flow<Resource<List<Games>>> = gamesRepository.getAllGames()

    override fun getFavoriteGames(): Flow<List<Games>> = gamesRepository.getFavoriteGames()

    override fun setFavoriteGames(games: Games, state: Boolean) = gamesRepository.setFavoriteGames(games, state)
}