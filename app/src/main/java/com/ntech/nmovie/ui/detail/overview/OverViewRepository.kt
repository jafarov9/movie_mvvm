package com.ntech.nmovie.ui.detail.overview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ntech.nmovie.data.remote.ApiClient
import com.ntech.nmovie.data.remote.ApiService
import com.ntech.nmovie.model.detail.MovieDetailResponse
import com.ntech.nmovie.model.videos.MovieVideoResponse
import com.ntech.nmovie.model.videos.MovieVideoResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OverViewRepository {

    private val apiService: ApiService by lazy { ApiClient.getApiService() }

    fun getDetails(movieId: Int?) : MutableLiveData<MovieDetailResponse> {

        val movieDetailLive : MutableLiveData<MovieDetailResponse> = MutableLiveData()

        apiService.getDetailedMovie(movieId).enqueue(object : Callback<MovieDetailResponse> {
            override fun onFailure(call: Call<MovieDetailResponse>, t: Throwable) {
                Log.e("getDetails", t.message!!)
            }

            override fun onResponse(
                call: Call<MovieDetailResponse>,
                response: Response<MovieDetailResponse>
            ) {

                movieDetailLive.value = response.body()


            }

        })

        return movieDetailLive
    }

    fun getMovieVideos(movieId: Int?) : MutableLiveData<List<MovieVideoResult>> {

        val movieVideoLive : MutableLiveData<List<MovieVideoResult>> = MutableLiveData()

        apiService.getMovieVideos(movieId).enqueue(object : Callback<MovieVideoResponse> {
            override fun onFailure(call: Call<MovieVideoResponse>, t: Throwable) {
                Log.e("getMovieVideos", t.message!!)
            }

            override fun onResponse(
                call: Call<MovieVideoResponse>,
                response: Response<MovieVideoResponse>
            ) {
                movieVideoLive.value = response.body()?.results
            }

        })

        return movieVideoLive
    }

}