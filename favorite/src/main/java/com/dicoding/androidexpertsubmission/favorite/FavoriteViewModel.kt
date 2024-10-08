package com.dicoding.androidexpertsubmission.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.androidexpertsubmission.core.domain.usecase.GamesUseCase

class FavoriteViewModel(gamesUseCase: GamesUseCase) : ViewModel() {

    val games = gamesUseCase.getFavoriteGames().asLiveData()
}