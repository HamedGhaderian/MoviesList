package com.developer.movieslist.di

import android.content.Context
import com.developer.movieslist.MoviesListApp
import com.developer.movieslist.ui.main.MainRepositoryModule
import com.developer.movieslist.utils.ViewModelBuilder
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created on : 4/28/2022
 * Author     : Hamed Ghaderian
 */
@Singleton
@Component(
    modules = [
        ActivityBindingModule::class,
        MainRepositoryModule::class,
        ApplicationModule::class,
        ViewModelBuilder::class,
        AndroidSupportInjectionModule::class]
)
interface AppComponent : AndroidInjector<MoviesListApp> {
    // Gives us syntactic sugar. we can then do DaggerAppComponent.builder().application(this).build().inject(this);
    // never having to instantiate any modules or say which module we are passing the application to.
    // Application will just be provided into our app graph now.
    //expose Application as an injectable context

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }
}