package com.developer.movieslist.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.developer.movieslist.utils.Event

/**
 * Created on : 4/28/2022
 * Author     : Hamed Ghaderian
 */
open class BaseViewModel : ViewModel() {

    private val _errorToast = MutableLiveData<Event<String?>>()
    val errorToast: LiveData<Event<String?>> = _errorToast

    fun showErrorToast(message: String?) {
        _errorToast.value = Event(message)
    }
}