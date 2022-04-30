package com.developer.movieslist.ui.main.data.source

import android.content.Context
import com.developer.movieslist.data.RequestResult
import com.developer.movieslist.data.models.ServerResponse

/**
 * Created on : 4/28/2022
 * Author     : Hamed Ghaderian
 */
interface MainRepository {
    suspend fun getMovies(context: Context, page: Int = 1): RequestResult<ServerResponse>
}