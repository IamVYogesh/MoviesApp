package com.example.movies.room

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.apiCall.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomViewmodel @Inject constructor(
    private val repository: RoomRepository
) : ViewModel(){

    fun storeMovie(movie: Movie, context: Context){

        viewModelScope.launch {
            try {
                repository.roomRepository(movie)
                Toast.makeText(context, "Stored to Room", Toast.LENGTH_SHORT).show()
            }catch (e : Exception){
                Toast.makeText(context, "Error Occurred", Toast.LENGTH_SHORT).show()
            }
        }
    }
}