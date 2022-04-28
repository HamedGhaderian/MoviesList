package com.developer.movieslist.ui.main.data.source

import com.developer.movieslist.data.RemoteDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * Created on : 4/28/2022
 * Author     : Hamed Ghaderian
 */
class MainDefaultRepository @Inject constructor(
    @RemoteDataSource private var mainRemoteDataSource: MainDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : MainRepository {
}