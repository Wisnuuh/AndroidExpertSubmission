package com.dicoding.androidexpertsubmission.ui.detail

import androidx.lifecycle.ViewModel
import com.dicoding.androidexpertsubmission.core.domain.model.Games
import com.dicoding.androidexpertsubmission.core.domain.usecase.GamesUseCase

class DetailViewModel(private val gamesUseCase: GamesUseCase): ViewModel() {

    fun setFavoriteGames(games: Games, newStatus:Boolean) =
        gamesUseCase.setFavoriteGames(games, newStatus)
}