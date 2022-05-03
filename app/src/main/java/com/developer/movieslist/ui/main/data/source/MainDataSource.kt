package com.developer.movieslist.ui.main.data.source

import android.content.Context
import com.developer.movieslist.data.models.ServerResponse
import retrofit2.Response

/**
 * Created on : 4/28/2022
 * Author     : Hamed Ghaderian
 */
interface MainDataSource {
    suspend fun getMovies(context: Context, page: Int): Response<ServerResponse>
}