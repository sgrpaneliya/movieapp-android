package com.movieapp.app.repository

import com.movieapp.app.RequestModel.MovieDetailRequestModel
import com.movieapp.app.WebService.ApiClient


/**
 * This class is use for a movie details repository
 * */
//TODO MovieDetailRepository
object MovieDetailRepository {

    suspend fun MovieDetails(movieDetailRequestModel: MovieDetailRequestModel, apikey: String) = ApiClient.getClient().MovieDetails(
        movieDetailRequestModel.MovieID!!,
        apikey
    )
}