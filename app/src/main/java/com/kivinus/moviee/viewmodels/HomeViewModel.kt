package com.kivinus.moviee.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.kivinus.moviee.api.TmdbRequestTypes
import com.kivinus.moviee.data.NetworkRepository


class HomeViewModel @ViewModelInject
constructor(private val repoNetwork: NetworkRepository) : ViewModel() {

    var currentQuery = TmdbRequestTypes.POPULAR

    var movies = repoNetwork.getMoviesByQuery(currentQuery)
        .cachedIn(viewModelScope)

    fun changeQuery(query: String) {
        currentQuery = query
        movies = repoNetwork.getMoviesByQuery(currentQuery)
            .cachedIn(viewModelScope)
    }
}

