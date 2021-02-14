package com.ntech.nmovie.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ntech.nmovie.R
import com.ntech.nmovie.common.ViewPagerAdapter
import com.ntech.nmovie.ui.main.favorites.FavoritesFragment
import com.ntech.nmovie.ui.main.popular.PopularMoviesFragment
import com.ntech.nmovie.ui.main.top_rated.TopRatedMoviesFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupUI()
    }

    private fun setupUI() {
        setSupportActionBar(main_toolbar)

        setupViewPager()

        main_tabs.setupWithViewPager(main_view_pager)
    }

    private fun setupViewPager() {
        val adapter = ViewPagerAdapter(supportFragmentManager)

        adapter.apply {
            addFragment(PopularMoviesFragment(), "Popular")
            addFragment(TopRatedMoviesFragment(), "Top rated")
            addFragment(FavoritesFragment(), "Favorites")
        }

        main_view_pager.adapter = adapter
        main_view_pager.offscreenPageLimit = 3
    }
}
