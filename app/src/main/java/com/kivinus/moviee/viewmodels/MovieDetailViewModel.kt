package com.kivinus.moviee.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kivinus.moviee.api.MapperMovieTmdb
import com.kivinus.moviee.data.LocalRepository
import com.kivinus.moviee.data.NetworkRepository
import com.kivinus.moviee.model.MovieEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch

class MovieDetailViewModel @ViewModelInject
constructor(
    private val repoLocal: LocalRepository,
    private val repoNetwork: NetworkRepository,
    private val mapperMovieTmdb: MapperMovieTmdb
) : ViewModel() {


    private val _selectedMovie: MutableStateFlow<MovieEntity?> = MutableStateFlow(null)
    val selectedMovie: StateFlow<MovieEntity?> = _selectedMovie

    private var isMovieInDb = false

    fun init(id: Int) {
        viewModelScope.launch {
            repoLocal.isRowIsExist(id).take(1).collect { isMovieInDb = it }
            if (isMovieInDb) {
                repoLocal.getMovieById(id).collect { _selectedMovie.value = it }
            } else {
                _selectedMovie.value = mapperMovieTmdb
                    .mapFromEntity(repoNetwork.getMovieById(id))
            }
        }
    }

    fun changeFavoriteStatus() {
        if (selectedMovie.value == null) return
        val movie = selectedMovie.value!!
        movie.isLiked = !(selectedMovie.value!!.isLiked)
        viewModelScope.launch {
            repoLocal.addMovie(movie)
        }
    }

}

