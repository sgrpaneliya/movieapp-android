package com.movieapp.app.viewModelFactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.movieapp.app.viewModel.MovieDetailViewModel

/**
 * This class is use for movie Detail viewmodel factory
 * */
//TODO MovieDetailViewModelFactory

class MovieDetailViewModelFactory  (private val application: Application) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieDetailViewModel(application) as T
    }
}