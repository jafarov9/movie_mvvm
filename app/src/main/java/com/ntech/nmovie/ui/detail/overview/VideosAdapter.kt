package com.ntech.nmovie.ui.detail.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ntech.nmovie.databinding.ItemVideoBinding
import com.ntech.nmovie.model.movie.MovieResults
import com.ntech.nmovie.model.videos.MovieVideoResult

class VideosAdapter : ListAdapter<MovieVideoResult, VideosAdapter.ViewHolder>(DIFF_CALLBACK) {

    private lateinit var onVideoClickListener: OnVideoClickListener

    fun setOnVideoClickListener(onVideoClickListener: OnVideoClickListener) {
        this.onVideoClickListener = onVideoClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
        = ViewHolder.create(LayoutInflater.from(parent.context), parent, onVideoClickListener)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position))

    class ViewHolder(val binding: ItemVideoBinding, onVideoClickListener: OnVideoClickListener): RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onVideoClickListener.onVideoClick(binding.video!!)
            }
        }

        companion object {
            fun create(inflater: LayoutInflater, parent: ViewGroup, onVideoClickListener: OnVideoClickListener): ViewHolder {
                val itemVideoBinding: ItemVideoBinding
                        = ItemVideoBinding.inflate(inflater, parent, false)
                return ViewHolder(itemVideoBinding, onVideoClickListener)
            }
        }

        fun bind(videoResult: MovieVideoResult) {
            binding.video = videoResult
            binding.executePendingBindings()
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieVideoResult>() {
            override fun areItemsTheSame(oldItem: MovieVideoResult, newItem: MovieVideoResult): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: MovieVideoResult, newItem: MovieVideoResult): Boolean =
                oldItem.name == newItem.name

        }
    }

    interface OnVideoClickListener {
        fun onVideoClick(videoResult: MovieVideoResult)
    }
}