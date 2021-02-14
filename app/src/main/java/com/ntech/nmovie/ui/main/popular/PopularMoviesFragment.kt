package com.ntech.nmovie.ui.main.popular

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
import kotlinx.android.synthetic.main.fragment_popular_movies.*

/**
 * A simple [Fragment] subclass.
 */
class PopularMoviesFragment : BaseVMFragment<PopularMoviesViewModel>(), MovieAdapter.OnMovieClickListener {

    private lateinit var adapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_popular_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        adapter = MovieAdapter()

        rv_popular.layoutManager = GridLayoutManager(activity, 2)

        adapter.setOnMovieClickListener(this)

        viewModel.moviePagedList.observe(this, Observer {
            adapter.submitList(it)
            rv_popular.adapter =  adapter
            rv_popular.visible()
            popular_progress_bar.gone()
        })


    }


    override fun getViewModel(): Class<PopularMoviesViewModel> = PopularMoviesViewModel::class.java


    override fun onMovieClick(movieResults: MovieResults?) {


        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra(Constants.EXTRA_MOVIES, movieResults)
        startActivity(intent)
    }


}
