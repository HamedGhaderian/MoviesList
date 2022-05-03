package com.developer.movieslist.utils

import android.content.Context
import android.net.ConnectivityManager
import com.developer.movieslist.BuildConfig
import com.developer.movieslist.data.remote.NetworkConnectionInterceptor
import com.developer.movieslist.utils.Const.BASE_URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Created on : 4/28/2022
 * Author     : Hamed Ghaderian
 */
object NetworkUtils {
    private var thread: Thread? = null
    private val gson: Gson = GsonBuilder()
        .setLenient()
        .create()

    fun getRetrofitInstance(context: Context): Retrofit? {
        val httpClient = getHttpClient(context)
        val builder = Retrofit.Builder().baseUrl(BASE_URL)
        builder.addConverterFactory(GsonConverterFactory.create(gson))
        builder.client(httpClient)
        return builder.build()
    }

    private fun getHttpClient(context: Context): OkHttpClient {
        val client = OkHttpClient.Builder()
        client.addInterceptor { chain: Interceptor.Chain ->
            val original = chain.request()
            val builder = original.newBuilder()
            builder.addHeader("Accept", " application/json")
            builder.addHeader("Authorization", " Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkZjI4MDAxN2Y0NmYzOThjZGU2NzI3NWU1MTY4NGU2NyIsInN1YiI6IjYyNmFhMDg1MmQ1MzFhMDljMTAzN2ZiMiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.jBnixkQ4d4HlKTTUMBuOQNjTSwFGl58Buy1k-ZUcLkY")
            builder.method(original.method, original.body)
            chain.proceed(builder.build())
        }
        client.addInterceptor(
            NetworkConnectionInterceptor(
                context
            )
        )


        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            client.addInterceptor(interceptor)
        }

        client.connectTimeout(20, TimeUnit.SECONDS)
        client.writeTimeout(20, TimeUnit.SECONDS)
        client.readTimeout(20, TimeUnit.SECONDS)
        client.retryOnConnectionFailure(false)
        return client.build()
    }

    fun isConnected(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = connectivityManager.activeNetworkInfo
        return netInfo != null && netInfo.isConnected
    }
}