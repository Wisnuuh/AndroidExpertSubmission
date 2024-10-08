package com.dicoding.androidexpertsubmission.core.data

import com.dicoding.androidexpertsubmission.core.data.source.local.LocalDataSource
import com.dicoding.androidexpertsubmission.core.data.source.remote.RemoteDataSource
import com.dicoding.androidexpertsubmission.core.data.source.remote.network.ApiResponse
import com.dicoding.androidexpertsubmission.core.data.source.remote.reponse.ResultsItem
import com.dicoding.androidexpertsubmission.core.domain.model.Games
import com.dicoding.androidexpertsubmission.core.domain.repository.IGamesRepository
import com.dicoding.androidexpertsubmission.core.utils.AppExecutors
import com.dicoding.androidexpertsubmission.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GamesRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
): IGamesRepository {

    companion object {

    }

    override fun getAllGames(): Flow<Resource<List<Games>>> =
        object : NetworkBoundResource<List<Games>, List<ResultsItem>>(appExecutors) {
            override fun loadFromDB(): Flow<List<Games>> {
                return localDataSource.getAllGames().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Games>?): Boolean =
                data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ResultsItem>>> =
                remoteDataSource.getAllGames()

            override suspend fun saveCallResult(data: List<ResultsItem>) {
                val gameList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertGames(gameList)
            }
        }.asLiveData()

    override fun getFavoriteGames(): Flow<List<Games>> {
        return localDataSource.getFavoriteGames().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteGames(games: Games, state: Boolean) {
        val gamesEntity = DataMapper.mapDomainToEntity(games)
        appExecutors.diskIO().execute { localDataSource.setFavoriteGames(gamesEntity, state) }
    }
}