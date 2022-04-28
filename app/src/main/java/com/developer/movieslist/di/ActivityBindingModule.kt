package com.developer.movieslist.di

import com.developer.movieslist.ui.main.MainActivity
import com.developer.movieslist.ui.main.MainModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created on : 4/28/2022
 * Author     : Hamed Ghaderian
 */
@Module
abstract class ActivityBindingModule {
    @ContributesAndroidInjector(
        modules = [MainModule::class]
    )
    abstract fun mainActivity(): MainActivity?

}