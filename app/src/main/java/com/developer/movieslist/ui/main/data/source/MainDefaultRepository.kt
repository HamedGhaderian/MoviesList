package com.developer.movieslist.ui.main.data.source

import android.content.Context
import com.developer.movieslist.data.RemoteDataSource
import com.developer.movieslist.data.RequestResult
import com.developer.movieslist.data.models.ServerResponse
import com.developer.movieslist.data.remote.ApiSuccessResponse
import com.developer.movieslist.data.remote.ResponseHandler
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

/**
 * Created on : 4/28/2022
 * Author     : Hamed Ghaderian
 */
class MainDefaultRepository @Inject constructor(
    @RemoteDataSource private var mainRemoteDataSource: MainDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : MainRepository {
    override suspend fun getMovies(
        context: Context, page: Int
    ): RequestResult<ServerResponse> {
        return withContext(ioDispatcher) {
            val result: Response<ServerResponse>
            try {
                result = mainRemoteDataSource.getMovies(context, page)
            } catch (e: Exception) {
                e.printStackTrace()
                return@withContext RequestResult.Error(if (e.message == null) "Unxpected error" else e.message!!)
            }
            when (val response = ResponseHandler.create(context, result)) {
                is ApiSuccessResponse -> {
                    return@withContext RequestResult.Success(response.data)
                }
                else -> {
                    return@withContext RequestResult.Error("")
                }
            }
        }
    }
}