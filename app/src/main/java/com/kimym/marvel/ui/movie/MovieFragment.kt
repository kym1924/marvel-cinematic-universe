package com.kimym.marvel.ui.movie

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.kimym.marvel.R
import com.kimym.marvel.base.BaseFragment
import com.kimym.marvel.databinding.FragmentMovieBinding
import com.kimym.marvel.util.addOnScrollListenerForJankStats
import com.kimym.marvel.util.getMetricsStateHolder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment : BaseFragment<FragmentMovieBinding>(R.layout.fragment_movie) {
    private val viewModel by viewModels<MovieViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBindingVariables()
        initToolbarMenuClickListener()
        initScrollListenerForJankStats()
    }

    private fun initBindingVariables() {
        with(binding) {
            vm = viewModel
            adapter = MovieAdapter()
            executePendingBindings()
        }
    }

    private fun initToolbarMenuClickListener() {
        binding.toolbarMovie.setOnMenuItemClickListener { menu ->
            viewModel.setPhase(menu.itemId)
            true
        }
    }

    private fun initScrollListenerForJankStats() {
        val holder = binding.getMetricsStateHolder()
        binding.rvMovie.addOnScrollListenerForJankStats(holder)
    }
}
