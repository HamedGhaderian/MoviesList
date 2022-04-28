package com.developer.movieslist.ui.main

import com.developer.movieslist.data.RemoteDataSource
import com.developer.movieslist.ui.main.data.source.MainDataSource
import com.developer.movieslist.ui.main.data.source.remote.MainRemoteDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created on : 4/28/2022
 * Author     : Hamed Ghaderian
 */
@Module
object MainRepositoryModule {

    @Singleton
    @Provides
    @RemoteDataSource
    fun provideMainRemoteDataSource(): MainDataSource {
        return MainRemoteDataSource()
    }
}