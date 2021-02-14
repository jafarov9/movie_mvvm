package com.ntech.nmovie.ui.main.top_rated

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.ntech.nmovie.model.movie.MovieResults

class TopRatedMovieDataSourceFactory: DataSource.Factory<Int, MovieResults>() {

    val popularMoviesLiveDataSource = MutableLiveData<TopRatedMoviesDataSource>()


    override fun create(): DataSource<Int, MovieResults> {
        val popularMovieDataSource = TopRatedMoviesDataSource()

        popularMoviesLiveDataSource.postValue(popularMovieDataSource)
        return popularMovieDataSource
    }
}