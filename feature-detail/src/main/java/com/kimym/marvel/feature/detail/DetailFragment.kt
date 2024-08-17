package com.kimym.marvel.feature.detail

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.kimym.marvel.core.ui.NavigateCallback
import com.kimym.marvel.core.ui.fragment.BaseFragment
import com.kimym.marvel.core.ui.fragment.FragmentResultKey
import com.kimym.marvel.feature.detail.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>(R.layout.fragment_detail) {
    private val viewModel by viewModels<DetailViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBindingVariables()
        initLayoutParams(binding.appBarDetail)
        initLayoutParams(binding.imgDetail)
        initToolbarNavigationClickListener()
        initRatingBarChangeListener()
        initFragmentResultListener()
    }

    private fun initBindingVariables() {
        with(binding) {
            vm = viewModel
            callback = NavigateCallback { view, id -> navigateRatingDialog(view, id) }
            executePendingBindings()
        }
    }

    private fun navigateRatingDialog(view: View, id: Int?) {
        id?.let {
            val request = NavDeepLinkRequest.Builder
                .fromUri("marvel://rating?id=$id".toUri())
                .build()
            view.findNavController().navigate(request)
        }
    }

    private fun initLayoutParams(view: View) {
        view.updateLayoutParams {
            width = ViewGroup.LayoutParams.MATCH_PARENT
            height = (resources.displayMetrics.heightPixels * 0.7).toInt()
        }
    }

    private fun initToolbarNavigationClickListener() {
        binding.toolbarDetail.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initRatingBarChangeListener() {
        binding.ratingDetail.setOnRatingBarChangeListener { _, value, _ ->
            viewModel.upsertRating(value)
        }
    }

    private fun initFragmentResultListener() {
        setFragmentResultListener(FragmentResultKey.KEY) { _, bundle ->
            viewModel.upsertRating(bundle.getFloat(FragmentResultKey.KEY))
            Snackbar.make(binding.root, "Star ratings are saved.", Snackbar.LENGTH_SHORT).show()
        }
    }
}
