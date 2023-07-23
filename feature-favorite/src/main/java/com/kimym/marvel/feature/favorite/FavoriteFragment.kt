package com.kimym.marvel.feature.favorite

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.kimym.marvel.core.ui.fragment.BaseFragment
import com.kimym.marvel.core.ui.jankstats.addOnScrollListenerForJankStats
import com.kimym.marvel.core.ui.jankstats.getMetricsStateHolder
import com.kimym.marvel.feature.favorite.databinding.FragmentFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>(R.layout.fragment_favorite) {
    private val viewModel by viewModels<FavoriteViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBindingVariables()
        initScrollListenerForJankStats()
    }

    private fun initBindingVariables() {
        with(binding) {
            vm = viewModel
            adapter = FavoriteAdapter()
            executePendingBindings()
        }
    }

    private fun initScrollListenerForJankStats() {
        val holder = binding.getMetricsStateHolder()
        binding.rvFavorite.addOnScrollListenerForJankStats(holder)
    }
}
