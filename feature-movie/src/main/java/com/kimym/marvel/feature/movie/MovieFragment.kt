package com.kimym.marvel.feature.movie

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.kimym.marvel.core.ui.fragment.BaseFragment
import com.kimym.marvel.core.ui.jankstats.addOnScrollListenerForJankStats
import com.kimym.marvel.core.ui.jankstats.getMetricsStateHolder
import com.kimym.marvel.feature.movie.databinding.FragmentMovieBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment : BaseFragment<FragmentMovieBinding>(R.layout.fragment_movie) {
    private val viewModel by viewModels<MovieViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBindingVariables()
        initScrollListenerForJankStats()
    }

    private fun initBindingVariables() {
        with(binding) {
            vm = viewModel
            adapter = MovieAdapter()
            executePendingBindings()
        }
    }

    private fun initScrollListenerForJankStats() {
        val holder = binding.getMetricsStateHolder()
        binding.rvMovie.addOnScrollListenerForJankStats(holder)
    }
}
