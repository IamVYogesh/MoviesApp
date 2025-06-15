package com.example.movies.firebase

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.apiCall.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FirebaseViewmodel @Inject constructor(
    private val repository: FirebaseRepository
) : ViewModel(){

    fun storeToFirebase(movie: Movie, context: Context){
        viewModelScope.launch {
            repository.firebaseRepository(movie, context)
        }
    }
}