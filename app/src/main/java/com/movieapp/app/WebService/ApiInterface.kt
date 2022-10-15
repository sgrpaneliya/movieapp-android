package com.movieapp.app.WebService

import com.movieapp.app.Models.MovieDetailModel
import com.movieapp.app.Models.MovieList
import com.movieapp.app.WebService.ApiConstant.MOVIEDETAILS
import com.movieapp.app.WebService.ApiConstant.MOVIELIST
import retrofit2.Response
import retrofit2.http.*

//TODO ApiInterface

interface ApiInterface {

    @GET(MOVIELIST)
    suspend fun MovieList(
        @Query("api_key") apiKey:String
    ): Response<MovieList>

    @GET(MOVIEDETAILS)
    suspend fun MovieDetails(
        @Path("movie_id") movie_id:String,
        @Query("api_key") apiKey:String
    ): Response<MovieDetailModel>


}