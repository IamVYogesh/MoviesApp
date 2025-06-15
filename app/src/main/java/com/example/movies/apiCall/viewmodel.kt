package com.example.movies.apiCall

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


@HiltViewModel
class FakeViewModel @Inject constructor(
    private val repo: Repo,
) : ViewModel() {


    private var _movies = MutableLiveData<List<Movie>>(emptyList())
    val movies : LiveData<List<Movie>> = _movies

    private var _currentPage = MutableLiveData(1)
    val currentPage : LiveData<Int> = _currentPage

    init {
        getMovies()
    }


    fun getMovies(page : Int = _currentPage.value?:1){
        viewModelScope.launch {
            repo.getMovies(page)
                .collect{ movies->
                    _movies.value = movies
                }
        }
    }

    fun nextPage() {
        val next = (_currentPage.value ?: 1) + 1
        _currentPage.value = next
        getMovies(next)
    }

    fun prevPage() {
        val prev = (_currentPage.value ?: 1) - 1
        _currentPage.value = prev
        getMovies(prev)
    }
}