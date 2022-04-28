package com.developer.movieslist.data.remote

import android.content.Context
import retrofit2.Response

/**
 * Created on : 4/28/2022
 * Author     : Hamed Ghaderian
 */
abstract class ResponseHandler {
    companion object {
        fun <T> create(context: Context, response: Response<T>): ApiResponse<T> {
            return if (response.isSuccessful && response.body() != null) {
                ApiSuccessResponse(response.body()!!)
            } else {
                ApiErrorResponse(response.message())
            }
        }
    }
}

class ApiSuccessResponse<T>(val data: T) : ApiResponse<T>()
class ApiErrorResponse<T>(val errorMessage: String, val data: T? = null) : ApiResponse<T>()
