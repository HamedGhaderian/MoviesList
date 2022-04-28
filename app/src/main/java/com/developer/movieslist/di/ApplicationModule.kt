package com.developer.movieslist.di

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

/**
 * Created on : 4/28/2022
 * Author     : Hamed Ghaderian
 */
@Module(includes = [ApplicationModuleBinds::class])
object ApplicationModule {
    @Singleton
    @Provides
    fun provideIoDispatcher() = Dispatchers.IO
}

@Module
abstract class ApplicationModuleBinds