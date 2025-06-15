package com.example.movies.room


import com.example.movies.apiCall.Movie
import javax.inject.Inject


class RoomRepository @Inject constructor(
    private val db : MovieDatabase
){
    suspend fun roomRepository(movie: Movie) {

        val movieToStore = MovieDetails(
            movie.originalTitle,
            movie.voteAverage,
            movie.releaseDate,
            movie.originalLanguage
        )
        db.movieDao().storeMovie(movieToStore)

    }
}


