package com.developer.movieslist.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.developer.movieslist.R
import com.developer.movieslist.data.models.Movie
import com.developer.movieslist.databinding.ItemMovieBinding
import com.developer.movieslist.utils.Const

/**
 * Created on : 4/30/2022
 * Author     : Hamed Ghaderian
 */
class MoviesListAdapter(private val onLoadMoreListener: () -> Unit) :
    ListAdapter<Movie, MoviesListAdapter.ViewHolder>(DiffCallback()) {

    private var isLoading: Boolean = false

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            private var lastVisibleItem: Int = 0
            private var totalItemCount: Int = 0
            private val visibleThreshold = 2

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (!isLoading) {
                    val linearLayoutManager =
                        recyclerView.layoutManager as LinearLayoutManager
                    totalItemCount = linearLayoutManager.itemCount
                    lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition()
                    if (totalItemCount <= lastVisibleItem + visibleThreshold) {
                        if (totalItemCount > visibleThreshold && !isLoading) {
                            onLoadMoreListener.invoke()
                            isLoading = true
                        }
                    }
                }
                super.onScrolled(recyclerView, dx, dy)
            }

        })
        super.onAttachedToRecyclerView(recyclerView)
    }


    fun dataIsLoaded() {
        this.isLoading = false
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), this.isLoading, position == currentList.size - 1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Movie, isLoading: Boolean, isLastItem: Boolean) {
            if (isLoading && isLastItem) {
                binding.contentContainer.visibility = View.GONE
                binding.loadingPb.visibility = View.VISIBLE
            } else {
                binding.contentContainer.visibility = View.VISIBLE
                binding.loadingPb.visibility = View.GONE
                binding.movie = item

                Glide.with(itemView.context)
                    .load(Const.IMAGE_BASE_URL + item.posterUrl)
                    .placeholder(
                        ContextCompat.getDrawable(
                            itemView.context,
                            R.drawable.placeholder
                        )
                    )
                    .into(binding.posterIv)
            }

            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemMovieBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }

    companion object {
        /**
         * Callback for calculating the diff between two non-null items in a list.
         *
         * Used by ListAdapter to calculate the minimum number of changes between and old list and a new
         * list that's been passed to `submitList`.
         */
        private class DiffCallback : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }

        }
    }
}

