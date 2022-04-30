package com.developer.movieslist.base

import android.widget.Toast
import dagger.android.support.DaggerAppCompatActivity

/**
 * Created on : 4/28/2022
 * Author     : Hamed Ghaderian
 */
open class BaseActivity : DaggerAppCompatActivity() {

    fun initErrorMessage(viewModel: BaseViewModel) {
        viewModel.errorToast.observe(this) { event ->
            event.getContentIfNotHandled()?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }
    }
}