package com.example.movies.apiCall

import android.app.VoiceInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import javax.inject.Inject

class Repo @Inject constructor(private val api: MoviesApi) {

    fun getMovies(page: Int): Flow<List<Movie>> = flow {
        val response = api.getMovies(page)
        emit(response.data)
    }.catch { e ->
        emit(emptyList())
    }
}


