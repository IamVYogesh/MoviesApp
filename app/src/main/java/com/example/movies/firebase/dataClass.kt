package com.example.movies.firebase


data class Casts(
    val picUrl : String = "",
    val castName : String = "",
    val popularity : String = ""
)


data class MovieDetails(
    val posterUrl : String = "",
    val movieName : String = "",
    val overview : String = "",
    val rating : Double = 0.0,
    val cast : List<Casts> = emptyList()
)

