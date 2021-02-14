package com.ntech.nmovie.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ntech.nmovie.model.movie.MovieResults

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movieResults: MovieResults?)

    @Delete
    fun deleteMovie(movieResults: MovieResults?)

    @Query("SELECT * FROM movies")
    fun getAllMovies() : LiveData<List<MovieResults>>

    @Query("SELECT * FROM movies WHERE movieId = :movieId")
    fun getSingleMovie(movieId: Int?) : LiveData<MovieResults>

}