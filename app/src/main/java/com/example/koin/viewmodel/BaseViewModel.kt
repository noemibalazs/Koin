package com.example.koin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<T> : ViewModel() {

    abstract fun loadingMovies(): LiveData<T>
}