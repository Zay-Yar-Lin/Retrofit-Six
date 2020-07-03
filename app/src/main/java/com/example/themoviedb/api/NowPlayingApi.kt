package com.example.themoviedb.api

import com.example.themoviedb.model.nowplaying.NowPlaying
import com.example.themoviedb.model.nowplaying.NowPlayingDetail
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class NowPlayingApi {
    private val nowPlayingInterface: NowPlayingInterface
    companion object{
        const val BASE_URL = "https://api.themoviedb.org/3/movie/"
    }
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        nowPlayingInterface = retrofit.create(NowPlayingInterface::class.java)
    }
    fun getNowPlaying(apiKey: String): Call<NowPlaying> = nowPlayingInterface.getNowPlaying(apiKey)

    fun getNowPlayingDetail(movieID:Int,apiKey: String): Call<NowPlayingDetail>{
        return nowPlayingInterface.getNowPlayingDetail(movieID, apiKey)
    }
}