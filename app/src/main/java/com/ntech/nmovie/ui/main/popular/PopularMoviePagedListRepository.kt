package com.ntech.nmovie.ui.main.popular

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ntech.nmovie.data.remote.ApiClient
import com.ntech.nmovie.data.remote.ApiService
import com.ntech.nmovie.data.remote.POST_PER_PAGE
import com.ntech.nmovie.model.movie.MovieResults

class PopularMoviePagedListRepository {


    lateinit var moviePagedList: LiveData<PagedList<MovieResults>>
    lateinit var moviesDataSourceFactory: PopularMovieDataSourceFactory

    fun fetchLiveMoviePagedList() : LiveData<PagedList<MovieResults>>{
        moviesDataSourceFactory = PopularMovieDataSourceFactory()

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(POST_PER_PAGE)
            .build()

        moviePagedList = LivePagedListBuilder(moviesDataSourceFactory, config).build()
        return moviePagedList
    }


}