package com.developer.movieslist.data.remote

import android.content.Context
import com.developer.movieslist.utils.NetworkUtils

/**
 * Created on : 4/28/2022
 * Author     : Hamed Ghaderian
 */
object ApiProvider {
    fun <S> createService(context: Context, serviceClass: Class<S>): S {
        return NetworkUtils.getRetrofitInstance(context)!!.create(serviceClass)
    }
}