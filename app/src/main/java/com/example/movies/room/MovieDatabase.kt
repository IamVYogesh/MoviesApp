package com.example.movies.room

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [MovieDetails::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao() : MovieDao
}