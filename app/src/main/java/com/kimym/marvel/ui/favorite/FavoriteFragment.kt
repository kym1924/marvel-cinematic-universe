package com.kimym.marvel.ui.favorite

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.kimym.marvel.R
import com.kimym.marvel.base.BaseFragment
import com.kimym.marvel.databinding.FragmentFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>(R.layout.fragment_favorite) {
    private val viewModel by viewModels<FavoriteViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBindingVariables()
    }

    private fun initBindingVariables() {
        with(binding) {
            vm = viewModel
            adapter = FavoriteAdapter()
            executePendingBindings()
        }
    }
}
