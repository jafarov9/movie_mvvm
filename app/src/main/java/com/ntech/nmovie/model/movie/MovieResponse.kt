package com.ntech.nmovie.model.movie

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("results")
    var results: List<MovieResults>,

    var page: Int,

    @SerializedName("total_pages")
    var totalPages: Int,

    @SerializedName("total_results")
    var totalResults: Int
)