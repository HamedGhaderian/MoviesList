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
    @SerializedName("total_pages")
    @Expose
    val totalPages: Int,
    @SerializedName("results")
    @Expose
    val results: MutableList<Movie>
)
