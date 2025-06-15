package com.example.movies.apiCall

import com.google.gson.annotations.SerializedName

data class Cast(
    val id: String,
    @SerializedName("movie_id")
    val movieId: Int,
    val name: String,
    @SerializedName("original_name")
    val originalName: String,
    val popularity: String,
    @SerializedName("profile_path")
    val profilePath: String,
    val character: String,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("updated_at")
    val updatedAt: String?
)

data class Movie(
    val id: String,
    @SerializedName("movie_id")
    val movieId: Int,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("original_language")
    val originalLanguage: String,
    val overview: String,
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int,
    val adult: Int,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("updated_at")
    val updatedAt: String?,
    val casts: List<Cast>
)


data class MovieResponse(
    @SerializedName("current_page")
    val currentPage: Int,
    val data: List<Movie>
)
