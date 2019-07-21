package com.example.koin.di

import com.example.koin.helper.SharedPrefHelper
import com.example.koin.network.MovieService
import com.example.koin.repository.PopularRepository
import com.example.koin.repository.ReviewRepository
import com.example.koin.repository.TopRatedRepository
import com.example.koin.repository.TrailerRepository
import com.example.koin.room.MovieDao
import com.example.koin.viewmodel.PopularViewModel
import com.example.koin.viewmodel.ReviewViewModel
import com.example.koin.viewmodel.TopRatedViewModel
import com.example.koin.viewmodel.TrailerViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val networkModule = module {
    single { MovieService.getServiceInstance() }
}

val sharedPrefModule = module {
    single { SharedPrefHelper(androidContext()) }
}

val dataBaseModule = module {
    single {MovieDao.getDataBaseInstance(androidContext()) }
}

val repoModule = module {
    single { PopularRepository(get(), get()) }
    single { TopRatedRepository(get(), get()) }
    single { ReviewRepository(get(), get()) }
    single { TrailerRepository(get(), get() ) }
}

val viewModule = module {
    viewModel { PopularViewModel(get()) }
    viewModel { TopRatedViewModel(get()) }
    viewModel { TrailerViewModel(get()) }
    viewModel { ReviewViewModel(get()) }
}