package com.kivinus.moviee.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kivinus.moviee.data.LocalRepository
import com.kivinus.moviee.data.NetworkRepository
import com.kivinus.moviee.model.MovieEntity
import kotlinx.coroutines.launch

class MovieDetailViewModel @ViewModelInject
constructor(
    private val repo: LocalRepository
) : ViewModel() {

    var selectedMovieId: Int = -1

    fun addToFavorite(movie: MovieEntity) {
        movie.isLiked = true
        viewModelScope.launch {
            repo.addMovie(movie)
        }
    }

    fun removeFromFavorite(movie: MovieEntity) {
        movie.isLiked = false
        viewModelScope.launch {
            repo.addMovie(movie)
        }
    }

}