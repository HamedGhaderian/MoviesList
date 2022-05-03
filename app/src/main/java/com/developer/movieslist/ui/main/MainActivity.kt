package com.developer.movieslist.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.developer.movieslist.base.BaseActivity
import com.developer.movieslist.data.models.Movie
import com.developer.movieslist.data.models.ServerResponse
import com.developer.movieslist.databinding.ActivityMainBinding
import javax.inject.Inject


class MainActivity : BaseActivity() {


    private var totalList: MutableList<Movie> = mutableListOf()
    private var previousList: MutableList<Movie> = mutableListOf()
    private var currentPage: Int = 0
    private lateinit var movieResult: ServerResponse
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
        initView()
        initObserves()

        viewModel.getMovies(this, 1, false)
    }

    private fun initView() {
        adapter = MoviesListAdapter {
            if (currentPage < movieResult.totalPages) {
                totalList.add(Movie())
                adapter.notifyItemInserted(adapter.currentList.size - 1)
                viewModel.getMovies(this, ++currentPage, true)
            }
        }
        mBinding.moviesListRv.adapter = adapter
    }

    private fun initObserves() {
        viewModel.onMoviesLoad.observe(this) { event ->
            event.getContentIfNotHandled()?.let { serverResponse ->
                if (previousList.isNotEmpty()) {
                    adapter.dataIsLoaded()
                    adapter.notifyItemRemoved(adapter.currentList.size - 1)
                    previousList.remove(adapter.currentList.last())
                }
                movieResult = serverResponse
                currentPage = movieResult.page
                totalList = previousList
                totalList.addAll(serverResponse.results)
                adapter.submitList(totalList)
                previousList = totalList
            }
        }

        viewModel.loading.observe(this) { event ->
            event.getContentIfNotHandled()?.let { visibility ->
                if (visibility) {
                    mBinding.loadingPb.visibility = View.VISIBLE
                    mBinding.moviesListRv.visibility = View.INVISIBLE
                } else {
                    mBinding.loadingPb.visibility = View.GONE
                    mBinding.moviesListRv.visibility = View.VISIBLE
                }
            }
        }

        viewModel.errorToast.observe(this) { event ->
            event.getContentIfNotHandled()?.let {
                if (this::adapter.isInitialized) {
                    adapter.dataIsLoaded()
                    adapter.notifyItemRemoved(adapter.currentList.size - 1)
                    previousList.remove(previousList.last())
                }

                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }

    }
}