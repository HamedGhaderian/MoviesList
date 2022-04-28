package com.developer.movieslist.ui.main

import android.os.Bundle
import com.developer.movieslist.base.BaseActivity
import com.developer.movieslist.databinding.ActivityMainBinding


class MainActivity : BaseActivity() {

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mBinding.root
        setContentView(view)
        //

    }
}