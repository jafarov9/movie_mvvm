package com.ntech.nmovie.ui.detail

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.google.android.material.appbar.AppBarLayout
import com.ntech.nmovie.R
import com.ntech.nmovie.common.BaseActivity
import com.ntech.nmovie.common.ViewPagerAdapter
import com.ntech.nmovie.databinding.ActivityDetailBinding
import com.ntech.nmovie.model.movie.MovieResults
import com.ntech.nmovie.ui.detail.overview.OverViewFragment
import com.ntech.nmovie.util.Constants
import kotlinx.android.synthetic.main.activity_detail.*
import kotlin.math.abs

class DetailActivity : BaseActivity<ActivityDetailBinding, DetailViewModel>() {

    private var movie: MovieResults? = null
    private var isFav: Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setupUI()

        intent.extras.let {
            movie = it?.getParcelable(Constants.EXTRA_MOVIES)

            setupViewpager(movie)

            fabBehavior(movie)

            detail_tabs.setupWithViewPager(detail_viewpager)

            checkFav()

            favorite_fab.setOnClickListener {
                if(isFav!!) {
                    viewModel.deleteMovie(movie!!)
                    Toast.makeText(this, "Favorilerden cikarildi!", Toast.LENGTH_LONG).show()
                }else {
                    viewModel.insertMovie(movie!!)
                    Toast.makeText(this, "Favorilere eklendi!", Toast.LENGTH_LONG).show()

                }
            }

            dataBinding.movie = movie
        }
    }


    private fun setupUI() {
        setSupportActionBar(detail_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun setupViewpager(movie: MovieResults?) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.apply {
            addFragment(OverViewFragment.newInstance(movie), "Overview")
        }

        detail_viewpager.adapter = adapter
    }


    private fun fabBehavior(movie: MovieResults?) {
        detail_appbar_layout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->

            if(abs(verticalOffset) - appBarLayout.totalScrollRange == 0) {
                favorite_fab.hide()
                supportActionBar?.setDisplayShowTitleEnabled(true)
                detail_toolbar.title = movie?.title
            }else {
                favorite_fab.show()
                supportActionBar?.setDisplayShowTitleEnabled(false)
                detail_toolbar.title = ""
            }

        })

        detail_collapsing_toolbar.setExpandedTitleColor(resources.getColor(android.R.color.transparent))
    }

    private fun checkFav() {
        viewModel.getSingleMovie(movie?.movieId).observe(this, Observer {
            if(it != null) {
                favorite_fab.setImageResource(R.drawable.ic_star)
                isFav = true
            }else {
                favorite_fab.setImageResource(R.drawable.ic_star_border_black_24dp)
                isFav = false
            }
        })
    }


    override fun getLayoutRes(): Int = R.layout.activity_detail

    override fun getViewModel(): Class<DetailViewModel> = DetailViewModel::class.java
}
