package com.ntech.nmovie.model.detail

import com.google.gson.annotations.SerializedName

data class MovieDetailResponse(
    @SerializedName("id")
    var id: Int,

    @SerializedName("backdrop_path")
    var backDropPath: String,

    @SerializedName("poster_path")
    var posterPath: String,

    @SerializedName("overview")
    var overview: String,

    @SerializedName("release_date")
    var releaseDate: String,

    @SerializedName("video")
    var video: Boolean,

    @SerializedName("title")
    var title: String,

    @SerializedName("vote_count")
    var voteCount: Int,

    @SerializedName("vote_average")
    var vote_average: Double
)