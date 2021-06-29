package com.kivinus.moviee.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.kivinus.moviee.api.TmdbRequestTypes
import com.kivinus.moviee.data.NetworkRepository


class HomeViewModel @ViewModelInject
constructor(
    repoNetwork: NetworkRepository) : ViewModel() {

    private val currentQuery = MutableLiveData(TmdbRequestTypes.POPULAR)

    val movies = repoNetwork.getMoviesByQuery(currentQuery.value!!)
        .cachedIn(viewModelScope)

    fun getMoviesByQuery(query: String) {
        currentQuery.value = query
    }
}

