package com.kimym.marvel.feature.detail

import android.os.Bundle
import android.view.View
import androidx.core.net.toUri
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.kimym.marvel.core.ui.BaseFragment
import com.kimym.marvel.core.ui.NavigateCallback
import com.kimym.marvel.feature.detail.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>(R.layout.fragment_detail) {
    private val args by navArgs<DetailFragmentArgs>()

    @Inject
    lateinit var viewModelAssistedFactory: DetailViewModel.DetailAssistedFactory

    private val viewModel by viewModels<DetailViewModel> {
        DetailViewModel.provideDetailAssistedFactory(viewModelAssistedFactory, args.id)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBindingVariables()
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

    private fun initToolbarNavigationClickListener() {
        binding.toolbarDetail.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initRatingBarChangeListener() {
        binding.ratingDetail.setOnRatingBarChangeListener { _, value, _ ->
            viewModel.changeRating(value)
        }
    }

    private fun initFragmentResultListener() {
        setFragmentResultListener("rating") { _, bundle ->
            viewModel.insertRating(bundle.getFloat("rating"))
            Snackbar.make(binding.root, "Star ratings are saved.", Snackbar.LENGTH_SHORT).show()
        }
    }
}
