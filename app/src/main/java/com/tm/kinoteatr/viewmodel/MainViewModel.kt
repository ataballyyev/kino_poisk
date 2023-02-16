package com.tm.kinoteatr.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tm.kinoteatr.data.MovieRepository
import com.tm.kinoteatr.data.model.MoviesModel
import com.tm.kinoteatr.data.model.NetworkResult
import kotlinx.coroutines.launch

class MainViewModel(private val repository: MovieRepository) : ViewModel() {

    var mLiveTopMovies = MutableLiveData<NetworkResult<MoviesModel>>()

    fun getTopMovies() {
        viewModelScope.launch {
            mLiveTopMovies.value = repository.getTopMovies()
        }
    }
}