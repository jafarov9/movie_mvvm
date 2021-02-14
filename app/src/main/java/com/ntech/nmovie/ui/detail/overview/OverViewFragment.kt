package com.ntech.nmovie.ui.detail.overview

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.ntech.nmovie.R
import com.ntech.nmovie.common.BaseFragment
import com.ntech.nmovie.databinding.FragmentOverViewBinding
import com.ntech.nmovie.model.movie.MovieResults
import com.ntech.nmovie.model.videos.MovieVideoResult
import com.ntech.nmovie.util.Constants
import com.ntech.nmovie.util.gone
import com.ntech.nmovie.util.visible
import kotlinx.android.synthetic.main.fragment_over_view.*

/**
 * A simple [Fragment] subclass.
 */
class OverViewFragment : BaseFragment<FragmentOverViewBinding, OverviewViewModel>(), VideosAdapter.OnVideoClickListener {

    private lateinit var adapter: VideosAdapter

    companion object {

        private const val MOVIE_KEY = "movie_overview_key"

        fun newInstance(movieResults: MovieResults?) : OverViewFragment {
            val args = Bundle()
            args.putParcelable(MOVIE_KEY, movieResults)

            val fragment = OverViewFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val movie: MovieResults? = arguments?.getParcelable(MOVIE_KEY) as MovieResults?

        val movieId = movie?.movieId

        viewModel.getDetails(movieId).observe(this, Observer {
            dataBinding.details = it
        })

        detail_movie_videos_progress.visible()
        movie_videos_recyclerview.gone()

       viewModel.getMovieVideos(movieId).observe(this, Observer {
           adapter = VideosAdapter()
           adapter.setOnVideoClickListener(this)

           movie_videos_recyclerview.adapter = adapter
           adapter.submitList(it)

           detail_movie_videos_progress.gone()
           movie_videos_recyclerview.visible()
       })

    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return dataBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    override fun getViewModel(): Class<OverviewViewModel> = OverviewViewModel::class.java

    override fun getLayoutRes(): Int = R.layout.fragment_over_view


    override fun onVideoClick(videoResult: MovieVideoResult) {

        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(Constants.YOUTUBE_WATCH_URL + videoResult.key)
        startActivity(intent)
    }

}
