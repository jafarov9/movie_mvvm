package com.ntech.nmovie.data.remote

import com.ntech.nmovie.model.detail.MovieDetailResponse
import com.ntech.nmovie.model.movie.MovieResponse
import com.ntech.nmovie.model.reviews.MovieReviewResponse
import com.ntech.nmovie.model.videos.MovieVideoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    //Popular movies
    @GET("movie/popular")
    fun getPopularMovies(@Query("page") page: Int) : Call<MovieResponse>

    //Top rated movies
    @GET("movie/top_rated")
    fun getTopRatedMovies(@Query("page") page: Int) : Call<MovieResponse>



    //Details

    @GET("movie/{id}")
    fun getDetailedMovie(@Path("id") movieId : Int?) : Call<MovieDetailResponse>


    //Videos
    @GET("movie/{id}/videos")
    fun getMovieVideos(@Path("id") movieId : Int?) : Call<MovieVideoResponse>

    //Reviews
    @GET("movie/{id}/reviews")
    fun getMovieReviews(@Path("id") movieId : Int) : Call<MovieReviewResponse>

}