package com.ntech.nmovie.ui.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.ntech.nmovie.model.movie.MovieResults

class DetailViewModel(app:Application) : AndroidViewModel(app) {

    private val detailRepository : DetailRepository by lazy { DetailRepository(app.applicationContext) }

    fun insertMovie(movie: MovieResults) = detailRepository.insertMovie(movie)

    fun deleteMovie(movie: MovieResults) = detailRepository.deleteMovie(movie)

    fun getAllMovies(): LiveData<List<MovieResults>> = detailRepository.getAllMovies()

    fun getSingleMovie(movieId: Int?) : LiveData<MovieResults> = detailRepository.getSingleMovie(movieId)

}