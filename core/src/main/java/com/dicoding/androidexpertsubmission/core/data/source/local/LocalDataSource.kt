package com.dicoding.androidexpertsubmission.core.data.source.local

import com.dicoding.androidexpertsubmission.core.data.source.local.entity.GamesEntity
import com.dicoding.androidexpertsubmission.core.data.source.local.room.GamesDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val gamesDao: GamesDao) {

    companion object {

    }

    fun getAllGames(): Flow<List<GamesEntity>> = gamesDao.getAllGames()

    fun getFavoriteGames(): Flow<List<GamesEntity>> = gamesDao.getFavoriteGames()

    suspend fun insertGames(gamesList: List<GamesEntity>) = gamesDao.insertGames(gamesList)

    fun setFavoriteGames(games: GamesEntity, newState: Boolean) {
        games.isFavorite = newState
        gamesDao.updateFavoriteGames(games)
    }
}