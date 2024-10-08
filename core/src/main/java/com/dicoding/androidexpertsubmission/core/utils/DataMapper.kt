package com.dicoding.androidexpertsubmission.core.utils

import com.dicoding.androidexpertsubmission.core.data.source.local.entity.GamesEntity
import com.dicoding.androidexpertsubmission.core.data.source.remote.reponse.ResultsItem
import com.dicoding.androidexpertsubmission.core.domain.model.Games

object DataMapper {
    fun mapResponsesToEntities(input: List<ResultsItem>): List<GamesEntity> {
        val gamesList = ArrayList<GamesEntity>()
        input.map {
            val games = GamesEntity(
                rating = it.rating,
                backgroundImage = it.backgroundImage,
                name = it.name,
                id = it.id,
                released = it.released,
                isFavorite = false
            )
            gamesList.add(games)
        }
        return gamesList
    }

    fun mapEntitiesToDomain(input: List<GamesEntity>): List<Games> =
        input.map {
            Games(
                rating = it.rating,
                backgroundImage = it.backgroundImage,
                name = it.name,
                id = it.id,
                released = it.released,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Games) = GamesEntity(
        rating = input.rating,
        backgroundImage = input.backgroundImage,
        name = input.name,
        id = input.id,
        released = input.released,
        isFavorite = input.isFavorite
    )
}