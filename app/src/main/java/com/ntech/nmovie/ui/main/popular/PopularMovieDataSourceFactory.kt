package com.ntech.nmovie.ui.main.popular

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.ntech.nmovie.model.movie.MovieResults

class PopularMovieDataSourceFactory: DataSource.Factory<Int, MovieResults>() {

    val popularMoviesLiveDataSource = MutableLiveData<PopularMoviesDataSource>()


    override fun create(): DataSource<Int, MovieResults> {
        val popularMovieDataSource = PopularMoviesDataSource()

        popularMoviesLiveDataSource.postValue(popularMovieDataSource)
        return popularMovieDataSource
    }
}