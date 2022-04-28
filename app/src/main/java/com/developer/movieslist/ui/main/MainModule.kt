package com.developer.movieslist.ui.main

import androidx.lifecycle.ViewModel
import com.developer.movieslist.utils.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created on : 4/28/2022
 * Author     : Hamed Ghaderian
 */
@Module
abstract class MainModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindViewModel(viewModel: MainViewModel): ViewModel
}