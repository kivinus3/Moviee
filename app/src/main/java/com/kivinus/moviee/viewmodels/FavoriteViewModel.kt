package com.kivinus.moviee.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.kivinus.moviee.data.LocalRepository

class FavoriteViewModel @ViewModelInject constructor(
    private val repoLocal: LocalRepository
) : ViewModel() {

    val favoriteMovies = repoLocal.getFavoriteMovies()

}