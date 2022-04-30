package com.developer.movieslist.ui.main

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.developer.movieslist.base.BaseViewModel
import com.developer.movieslist.data.RequestResult
import com.developer.movieslist.data.models.ServerResponse
import com.developer.movieslist.ui.main.data.source.MainDefaultRepository
import com.developer.movieslist.utils.Event
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created on : 4/28/2022
 * Author     : Hamed Ghaderian
 */
class MainViewModel @Inject constructor(
    private val defaultRepository: MainDefaultRepository
) : BaseViewModel() {

    private val _onMoviesLoad = MutableLiveData<Event<ServerResponse>>()
    val onMoviesLoad: LiveData<Event<ServerResponse>> = _onMoviesLoad

    fun getMovies(context: Context, page: Int) {
        viewModelScope.launch {
            when (val result = defaultRepository.getMovies(context, page)) {
                is RequestResult.Success -> {
                    _onMoviesLoad.value = Event(result.data)
                }
                is RequestResult.Error -> {
                    showErrorToast(result.exception)
                }
            }
        }
    }
}