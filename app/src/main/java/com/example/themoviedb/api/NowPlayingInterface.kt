package com.example.themoviedb.api

import com.example.themoviedb.model.nowplaying.NowPlaying
import com.example.themoviedb.model.nowplaying.NowPlayingDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NowPlayingInterface {
    @GET("now_playing")
    fun getNowPlaying(
        @Query("api_key") apiKey: String
    ): Call<NowPlaying>
    @GET("{movie_id}")
    fun getNowPlayingDetail(
        @Path("movie_id") movieID : Int,
        @Query("api_key") apiKey: String
    ): Call<NowPlayingDetail>
}