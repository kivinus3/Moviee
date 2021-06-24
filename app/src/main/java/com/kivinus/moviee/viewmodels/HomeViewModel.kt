package com.kivinus.moviee.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.kivinus.moviee.api.TmdbRequestTypes
import com.kivinus.moviee.data.NetworkRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn


class HomeViewModel @ViewModelInject
constructor(private val repo: NetworkRepository) : ViewModel() {

    private val currentQuery = MutableLiveData(TmdbRequestTypes.POPULAR)

    val movies = repo.getMoviesByQuery(currentQuery.value!!)
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            PagingData.empty()
        )

    fun getMoviesByQuery(query: String) {
        currentQuery.value = query
    }
}

