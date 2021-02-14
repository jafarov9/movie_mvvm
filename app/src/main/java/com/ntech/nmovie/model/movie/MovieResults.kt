package com.ntech.nmovie.model.movie

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
data class MovieResults(

    @PrimaryKey
    @SerializedName("id")
    var movieId: Int,

    @SerializedName("poster_path")
    var posterPath: String?,

    @SerializedName("overview")
    var overview: String?,

    @SerializedName("release_date")
    var releaseDate: String?,

    @SerializedName("original_title")
    var originalTitle: String?,

    @SerializedName("original_language")
    var originalLanguage: String?,

    @SerializedName("title")
    var title: String?,

    @SerializedName("backdrop_path")
    var backDropPath: String?,

    @SerializedName("popularity")
    var popularity: Double,

    @SerializedName("vote_count")
    var voteCount: Int,

    @SerializedName("video")
    var video: Boolean,

    @SerializedName("vote_average")
    var vote_average: Double
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readByte() != 0.toByte(),
        parcel.readDouble()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(movieId)
        parcel.writeString(posterPath)
        parcel.writeString(overview)
        parcel.writeString(releaseDate)
        parcel.writeString(originalTitle)
        parcel.writeString(originalLanguage)
        parcel.writeString(title)
        parcel.writeString(backDropPath)
        parcel.writeDouble(popularity)
        parcel.writeInt(voteCount)
        parcel.writeByte(if (video) 1 else 0)
        parcel.writeDouble(vote_average)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MovieResults> {
        override fun createFromParcel(parcel: Parcel): MovieResults {
            return MovieResults(parcel)
        }

        override fun newArray(size: Int): Array<MovieResults?> {
            return arrayOfNulls(size)
        }
    }
}