package com.developer.movieslist.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created on : 4/30/2022
 * Author     : Hamed Ghaderian
 */
data class ServerResponse(
    @SerializedName("page")
    @Expose
    val page: Int,
    @SerializedName("results")
    @Expose
    val results: MutableList<Movie>
)
