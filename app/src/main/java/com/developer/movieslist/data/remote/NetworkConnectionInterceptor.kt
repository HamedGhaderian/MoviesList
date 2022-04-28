package com.developer.movieslist.data.remote

import android.content.Context
import com.developer.movieslist.utils.NetworkUtils.isConnected
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


/**
 * Created on : 4/28/2022
 * Author     : Hamed Ghaderian
 */
class NetworkConnectionInterceptor(private var context: Context) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isConnected(context)) {
            throw NoConnectivityException()
        }
        val builder: Request.Builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }
}