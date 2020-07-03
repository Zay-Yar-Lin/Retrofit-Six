package com.example.themoviedb.api
import com.example.themoviedb.model.MovieDetail
import com.example.themoviedb.model.TopRated
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieApi {
    private val movieInterface: MovieInterface

    companion object{
        const val BASE_URL= "https://api.themoviedb.org/3/movie/"
    }
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        movieInterface =retrofit.create(MovieInterface::class.java)
    }
    fun getTopRated (apiKey : String): Call<TopRated> = movieInterface.getTopRated(apiKey)

    fun getMovieDetail(movieID:Int,apiKey: String): Call<MovieDetail>{
        return movieInterface.getMovieDetail(movieID,apiKey)
    }
}