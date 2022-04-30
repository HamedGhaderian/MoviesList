package com.developer.movieslist.ui.main.data.source.remote

import android.content.Context
import com.developer.movieslist.data.models.ServerResponse
import com.developer.movieslist.data.remote.ApiProvider
import com.developer.movieslist.ui.main.data.source.MainDataSource
import retrofit2.Response
import javax.inject.Inject


/**
 * Created on : 4/28/2022
 * Author     : Hamed Ghaderian
 */
class MainRemoteDataSource @Inject constructor() : MainDataSource {
    override suspend fun getMovies(context: Context, page: Int): Response<ServerResponse> {
        return ApiProvider.createService(context, MainApi::class.java).getMovies(page = page)
    }
}