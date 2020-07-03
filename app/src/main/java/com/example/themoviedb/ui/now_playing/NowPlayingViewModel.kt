package com.example.themoviedb.ui.now_playing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.themoviedb.api.NowPlayingApi
import com.example.themoviedb.model.nowplaying.NowPlaying
import com.example.themoviedb.model.nowplaying.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class   NowPlayingViewModel : ViewModel() {

    var loading: MutableLiveData<Boolean> = MutableLiveData()
    var nowPlaying:MutableLiveData<List<Result>> = MutableLiveData()
    var loadError:MutableLiveData<Boolean> = MutableLiveData()

    fun getLoading() : LiveData<Boolean> = loading
    fun getNowPlaying() : LiveData<List<Result>> = nowPlaying
    fun getLoadError(): LiveData<Boolean> = loadError

    var nowPlayingApi = NowPlayingApi()

    fun loadNowPlaying() {
        loading.value = true
        var nowPlayingApiCall = nowPlayingApi.getNowPlaying("670bc282e8edaac05b2f85e6d1895435")
        nowPlayingApiCall.enqueue(object : Callback<NowPlaying> {
            override fun onFailure(call: Call<NowPlaying>, t: Throwable) {
                loading.value = false
                loadError.value = true
            }

            override fun onResponse(call: Call<NowPlaying>, response: Response<NowPlaying>) {
                var nowPlayingList = response.body()?.results ?: emptyList()
                nowPlaying.value = nowPlayingList
            }

        })
    }

}