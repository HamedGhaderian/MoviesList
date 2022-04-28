package com.developer.movieslist.data.remote

import java.io.IOException

/**
 * Created on : 4/28/2022
 * Author     : Hamed Ghaderian
 */
class NoConnectivityException : IOException() {
    override val message: String
        get() = "عدم اتصال به اینترنت!"
}