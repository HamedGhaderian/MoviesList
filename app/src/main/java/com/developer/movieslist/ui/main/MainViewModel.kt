package com.developer.movieslist.ui.main

import com.developer.movieslist.base.BaseViewModel
import com.developer.movieslist.ui.main.data.source.MainDefaultRepository
import javax.inject.Inject

/**
 * Created on : 4/28/2022
 * Author     : Hamed Ghaderian
 */
class MainViewModel @Inject constructor(
    private val defaultRepository: MainDefaultRepository
) : BaseViewModel() {


}