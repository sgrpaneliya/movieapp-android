package com.movieapp.app.repository

import com.movieapp.app.WebService.ApiClient

/**
 * This class is use for a movie list repository
 * */
//TODO MovieListRepository

object MovieListRepository {

    suspend fun MovieListSelect(apikey: String) = ApiClient.getClient().MovieList(
        apikey
    )
}