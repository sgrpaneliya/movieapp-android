package com.movieapp.app.views.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.movieapp.app.Adapter.MoviePackageAdapter
import com.movieapp.app.R
import com.movieapp.app.Utils.utils
import com.movieapp.app.databinding.ActivityMainBinding
import com.movieapp.app.viewModel.MovieListViewModel
import com.movieapp.app.viewModelFactory.MovieListViewModelFactory

//TODO MainActivity

class MainActivity : AppCompatActivity() {

    /**
     * use for a data binding
     * */
    private var binding: ActivityMainBinding? = null

    /**
     * use for a set view model factory
     * */
    private val movieViewModel: MovieListViewModel by viewModels {
        MovieListViewModelFactory(application)
    }

    private var moviePackageAdapter: MoviePackageAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding?.lifecycleOwner = this
        binding?.movieViewModel = movieViewModel

        setUpObservers()
    }

    /**
     * This function use for observed
     * */
    private fun setUpObservers() {

        /**
         * set api calling
         * */
        utils.Util.ShowProgress(this)
        movieViewModel.MovieListCall()

        /**
         * use for movie list response
         * */
        movieViewModel.MovieListResponse.observe(this) { movieData ->
            utils.Util.HideProgress()
            if (movieData.isNotEmpty()) {
                moviePackageAdapter = MoviePackageAdapter(this, movieData)
                binding!!.recyclerviewMovieList.adapter = moviePackageAdapter
            }
        }

        /**
         * use for set error
         * */
        movieViewModel.errorMessage.observe(this) { error ->
            utils.Util.HideProgress()
            utils.Util.DebugToast(this, error)
        }
    }
}