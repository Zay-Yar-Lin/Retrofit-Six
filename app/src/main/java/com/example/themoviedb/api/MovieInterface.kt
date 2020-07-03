package com.example.themoviedb.api

import com.example.themoviedb.model.MovieDetail
import com.example.themoviedb.model.TopRated
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieInterface {
    @GET("top_rated")
    fun getTopRated(
        @Query("api_key") apiKey : String
    ): Call<TopRated>
    @GET("{movie_id}")
    fun getMovieDetail(
        @Path("movie_id") movieId  : Int,
        @Query("api_key") apiKey: String
    ) : Call<MovieDetail>
}