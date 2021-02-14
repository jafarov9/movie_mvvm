package com.ntech.nmovie.ui.main.top_rated

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager

import com.ntech.nmovie.R
import com.ntech.nmovie.common.BaseVMFragment
import com.ntech.nmovie.model.movie.MovieResults
import com.ntech.nmovie.ui.detail.DetailActivity
import com.ntech.nmovie.ui.main.MovieAdapter
import com.ntech.nmovie.util.Constants
import com.ntech.nmovie.util.gone
import com.ntech.nmovie.util.visible
import kotlinx.android.synthetic.main.fragment_top_rated_movies.*

/**
 * A simple [Fragment] subclass.
 */
class TopRatedMoviesFragment : BaseVMFragment<TopRatedMoviesViewModel>() , MovieAdapter.OnMovieClickListener {

    private lateinit var adapter: MovieAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top_rated_movies, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        adapter = MovieAdapter()
        adapter.setOnMovieClickListener(this)

        rv_top_rated.layoutManager = GridLayoutManager(activity, 2)

        viewModel.moviePagedList.observe(this, Observer {
            adapter.submitList(it)
            rv_top_rated.adapter = adapter
            rv_top_rated.visible()
            top_rated_progress_bar.gone()
        })
    }


    override fun getViewModel(): Class<TopRatedMoviesViewModel> {
        return TopRatedMoviesViewModel::class.java
    }

    override fun onMovieClick(movieResults: MovieResults?) {

        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra(Constants.EXTRA_MOVIES, movieResults)
        startActivity(intent)

    }

}
