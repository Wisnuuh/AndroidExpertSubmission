package com.dicoding.androidexpertsubmission.di

import com.dicoding.androidexpertsubmission.core.domain.usecase.GamesInteractor
import com.dicoding.androidexpertsubmission.core.domain.usecase.GamesUseCase
import com.dicoding.androidexpertsubmission.ui.detail.DetailViewModel
import com.dicoding.androidexpertsubmission.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<GamesUseCase> { GamesInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}