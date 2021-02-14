package com.ntech.nmovie.ui.main.top_rated

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.ntech.nmovie.data.remote.ApiClient
import com.ntech.nmovie.data.remote.ApiService
import com.ntech.nmovie.data.remote.FIRST_PAGE
import com.ntech.nmovie.model.movie.MovieResponse
import com.ntech.nmovie.model.movie.MovieResults
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TopRatedMoviesDataSource : PageKeyedDataSource<Int, MovieResults>(){

    private var page = FIRST_PAGE
    private val apiService: ApiService by lazy { ApiClient.getApiService() }


    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, MovieResults>
    ) {
        apiService.getTopRatedMovies(page).enqueue(object: Callback<MovieResponse> {

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                response.body()?.results?.let {
                    callback.onResult(it, null, page + 1)
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e("getTopRatedMovies", t.message!!)
            }

        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, MovieResults>) {
        apiService.getTopRatedMovies(params.key).enqueue(object: Callback<MovieResponse> {

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {

                if(response.body()?.totalPages!! >= params.key) {
                    response.body()?.results?.let {
                        callback.onResult(it, params.key + 1)
                    }
                }else {

                }

            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e("getTopRatedMovies", t.message!!)
            }

        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, MovieResults>) {
        TODO("Not yet implemented")
    }


}