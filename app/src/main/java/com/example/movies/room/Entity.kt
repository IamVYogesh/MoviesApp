package com.example.movies.room

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "MovieDetails")
data class MovieDetails(
    @PrimaryKey val movieName : String,
    val rating : Double,
    val releaseDate : String,
    val originalLanguage : String
)