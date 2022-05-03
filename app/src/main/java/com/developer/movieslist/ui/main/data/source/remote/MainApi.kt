package com.developer.movieslist.ui.main.data.source.remote

import com.developer.movieslist.data.models.ServerResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created on : 4/28/2022
 * Author     : Hamed Ghaderian
 */
interface MainApi {
    @GET("discover/movie")
    suspend fun getMovies(
        @Query("api_key") apiKey: String = "df280017f46f398cde67275e51684e67",
        @Query("page") page: Int,
    ): Response<ServerResponse>
}