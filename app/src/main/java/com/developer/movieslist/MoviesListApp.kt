package com.developer.movieslist

import com.developer.movieslist.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

/**
 * Created on : 4/28/2022
 * Author     : Hamed Ghaderian
 */
class MoviesListApp : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(applicationContext)
    }
}