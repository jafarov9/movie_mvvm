package com.ntech.nmovie.ui.main.popular

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.ntech.nmovie.model.movie.MovieResults

class PopularMoviesViewModel(): ViewModel() {

    val moviePagedListRepository : PopularMoviePagedListRepository by lazy { PopularMoviePagedListRepository() }


    val moviePagedList : LiveData<PagedList<MovieResults>> by lazy {
        moviePagedListRepository.fetchLiveMoviePagedList()
    }

    fun listIsEmpty() : Boolean {
        return moviePagedList.value?.isEmpty() ?: true
    }

}