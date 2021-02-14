package com.ntech.nmovie.ui.main.top_rated

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.ntech.nmovie.model.movie.MovieResults
import com.ntech.nmovie.ui.main.popular.PopularMoviePagedListRepository

class TopRatedMoviesViewModel() : ViewModel() {

    val moviePagedListRepository : TopRatedMoviePagedListRepository by lazy { TopRatedMoviePagedListRepository() }


    val moviePagedList : LiveData<PagedList<MovieResults>> by lazy {
        moviePagedListRepository.fetchLiveMoviePagedList()
    }

    fun listIsEmpty() : Boolean {
        return moviePagedList.value?.isEmpty() ?: true
    }

}