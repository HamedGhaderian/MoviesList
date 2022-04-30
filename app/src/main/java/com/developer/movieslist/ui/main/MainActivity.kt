package com.developer.movieslist.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.developer.movieslist.base.BaseActivity
import com.developer.movieslist.databinding.ActivityMainBinding
import javax.inject.Inject


class MainActivity : BaseActivity() {

    private lateinit var adapter: MoviesListAdapter

    private lateinit var mBinding: ActivityMainBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<MainViewModel> {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mBinding.root
        setContentView(view)
        //
        initErrorMessage(viewModel)
        initView()
        initObserves()
    }

    private fun initView() {

        adapter = MoviesListAdapter()
        mBinding.moviesListRv.adapter = adapter
    }

    private fun initObserves() {
        viewModel.onMoviesLoad.observe(this) { event ->
            event.getContentIfNotHandled()?.let { serverResponse ->

            }
        }
    }
}