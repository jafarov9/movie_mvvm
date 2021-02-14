package com.ntech.nmovie.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ntech.nmovie.databinding.ItemMovieBinding
import com.ntech.nmovie.model.movie.MovieResults

class MovieAdapterForRoom : ListAdapter<MovieResults, MovieAdapterForRoom.ViewHolder>(DIFF_CALLBACK) {

    private lateinit var onMovieClickListener: MovieAdapterForRoom.OnMovieClickListener

    fun setOnMovieClickListener(onMovieClickListener: MovieAdapterForRoom.OnMovieClickListener) {
        this.onMovieClickListener = onMovieClickListener

    }


    class ViewHolder(val binding: ItemMovieBinding, onMovieClickListener: MovieAdapterForRoom.OnMovieClickListener) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onMovieClickListener.onMovieClick(binding.movie)
            }
        }

        companion object {
            fun create(inflater: LayoutInflater, parent: ViewGroup, onMovieClickListener: MovieAdapterForRoom.OnMovieClickListener) : ViewHolder {
                val itemMovieBinding = ItemMovieBinding.inflate(inflater, parent, false)
                return ViewHolder(itemMovieBinding, onMovieClickListener)
            }
        }

        fun bind(movieResults: MovieResults) {
            binding.movie = movieResults
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapterForRoom.ViewHolder =
        MovieAdapterForRoom.ViewHolder.create(LayoutInflater.from(parent.context), parent, onMovieClickListener)

    override fun onBindViewHolder(holder: MovieAdapterForRoom.ViewHolder, position: Int)  =
        holder.bind(getItem(position))

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieResults>() {
            override fun areItemsTheSame(oldItem: MovieResults, newItem: MovieResults): Boolean =
                oldItem.movieId == newItem.movieId

            override fun areContentsTheSame(oldItem: MovieResults, newItem: MovieResults): Boolean =
                oldItem.title == newItem.title

        }
    }


    interface OnMovieClickListener {

        fun onMovieClick(movieResults: MovieResults?)
    }
}