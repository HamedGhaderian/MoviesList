package com.developer.movieslist.utils

import android.content.Context
import android.net.ConnectivityManager
import androidx.databinding.ktx.BuildConfig
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

        client.connectTimeout(2, TimeUnit.MINUTES)
        client.writeTimeout(2, TimeUnit.MINUTES)
        client.readTimeout(2, TimeUnit.MINUTES)
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