package com.ntech.nmovie.ui.main.favorites

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
import com.ntech.nmovie.ui.detail.DetailViewModel
import com.ntech.nmovie.ui.main.MovieAdapterForRoom
import com.ntech.nmovie.util.Constants
import com.ntech.nmovie.util.gone
import com.ntech.nmovie.util.visible
import kotlinx.android.synthetic.main.fragment_favorites.*

/**
 * A simple [Fragment] subclass.
 */
class FavoritesFragment : BaseVMFragment<DetailViewModel>(), MovieAdapterForRoom.OnMovieClickListener {

    private lateinit var adapter: MovieAdapterForRoom

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun getViewModel(): Class<DetailViewModel> = DetailViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MovieAdapterForRoom()
        adapter.setOnMovieClickListener(this)
        favorites_recyclerview.layoutManager = GridLayoutManager(activity, 2)

        viewModel.getAllMovies().observe(this, Observer {
            adapter.submitList(it)

            favorites_recyclerview.adapter = adapter
            favorites_recyclerview.visible()
            favorites_progressbar.gone()
        })
    }

    override fun onMovieClick(movieResults: MovieResults?) {
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra(Constants.EXTRA_MOVIES, movieResults)
        startActivity(intent)
    }
}
