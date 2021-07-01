package com.kivinus.moviee.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kivinus.moviee.api.MapperMovieTmdb
import com.kivinus.moviee.data.LocalRepository
import com.kivinus.moviee.data.NetworkRepository
import com.kivinus.moviee.model.MovieEntity
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MovieDetailViewModel @ViewModelInject
constructor(
    private val repoLocal: LocalRepository,
    private val repoNetwork: NetworkRepository,
    private val mapperMovieTmdb: MapperMovieTmdb
) : ViewModel() {

    val selectedMovie: MutableStateFlow<MovieEntity?> = MutableStateFlow(null)
    private val isMovieInDb: MutableStateFlow<Boolean> = MutableStateFlow(false)

    fun init(id: Int) {
        viewModelScope.launch {
            repoLocal.isRowIsExist(id).take(1).collect { isMovieInDb.value = it }
            if (isMovieInDb.value) {
                repoLocal.getMovieById(id).collect { selectedMovie.value = it }
            } else {
                selectedMovie.value = mapperMovieTmdb
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

