package com.developer.movieslist.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created on : 4/30/2022
 * Author     : Hamed Ghaderian
 */
data class Movie(
    @SerializedName("id")
    @Expose
    val id: String = "",
    @SerializedName("title")
    @Expose
    val title: String = "",
    @SerializedName("overview")
    @Expose
    val overview: String = "",
    @SerializedName("poster_path")
    @Expose
    val posterUrl: String? = "",
)
