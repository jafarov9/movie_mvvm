package com.ntech.nmovie.ui.detail.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ntech.nmovie.model.detail.MovieDetailResponse
import com.ntech.nmovie.model.videos.MovieVideoResult

class OverviewViewModel: ViewModel() {

    private val repository: OverViewRepository by lazy { OverViewRepository() }


    fun getDetails(movieId: Int?) : LiveData<MovieDetailResponse>
            = repository.getDetails(movieId)

    fun getMovieVideos(movieId: Int?) : LiveData<List<MovieVideoResult>> =
        repository.getMovieVideos(movieId)

}