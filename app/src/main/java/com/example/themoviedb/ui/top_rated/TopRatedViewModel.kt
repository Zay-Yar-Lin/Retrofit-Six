package com.example.themoviedb.ui.top_rated

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.themoviedb.api.MovieApi
import com.example.themoviedb.model.Result
import com.example.themoviedb.model.TopRated
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TopRatedViewModel : ViewModel(){

    var loading: MutableLiveData<Boolean> = MutableLiveData()
    var topRated:MutableLiveData<List<Result>> = MutableLiveData()
    var loadError:MutableLiveData<Boolean> = MutableLiveData()

    fun getLoading(): LiveData<Boolean> = loading
    fun getTopRated():LiveData<List<Result>> = topRated
    fun getLoadError():LiveData<Boolean> = loadError

    var movieApi = MovieApi()

    fun loadTopRated(){
        loading.value = true
        var topRatedApiCall = movieApi.getTopRated("670bc282e8edaac05b2f85e6d1895435")
        topRatedApiCall.enqueue(object : Callback<TopRated>{
            override fun onFailure(call: Call<TopRated>, t: Throwable) {
                loading.value = false
                loadError.value = true
            }

            override fun onResponse(call: Call<TopRated>, response: Response<TopRated>) {
                var topRatedList = response.body()?.results ?: emptyList()
                topRated.value = topRatedList
            }

        })
    }
}