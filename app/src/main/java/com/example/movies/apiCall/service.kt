package com.example.movies.apiCall

import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {
    @GET("/movies/paginated")
    suspend fun getMovies(@Query("current_page") page : Int) : MovieResponse

}

