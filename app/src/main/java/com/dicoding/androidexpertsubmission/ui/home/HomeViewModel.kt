package com.dicoding.androidexpertsubmission.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.androidexpertsubmission.core.domain.usecase.GamesUseCase

class HomeViewModel(gamesUseCase: GamesUseCase) : ViewModel() {

    val games = gamesUseCase.getAllGames().asLiveData()
}