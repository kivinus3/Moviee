package com.kivinus.moviee.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.kivinus.moviee.data.LocalRepository
import com.kivinus.moviee.model.MovieEntity
import kotlinx.coroutines.flow.Flow

class FavoriteViewModel @ViewModelInject constructor(
    repoLocal: LocalRepository
) : ViewModel() {

    val favoriteMovies: Flow<List<MovieEntity>> = repoLocal.getFavoriteMovies()

}