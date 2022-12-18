package com.kimym.marvel.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.kimym.marvel.databinding.FragmentDetailBinding
import com.kimym.marvel.util.NavigateCallback
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = requireNotNull(_binding)
    private val args by navArgs<DetailFragmentArgs>()

    @Inject
    lateinit var viewModelAssistedFactory: DetailViewModel.DetailAssistedFactory

    private val viewModel by viewModels<DetailViewModel> {
        DetailViewModel.provideDetailAssistedFactory(viewModelAssistedFactory, args.title)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBindingVariables()
        initToolbarNavigationClickListener()
        initFragmentResultListener()
    }

    private fun initBindingVariables() {
        with(binding) {
            viewModel = this@DetailFragment.viewModel
            callback = NavigateCallback { view, title ->
                title?.let {
                    val action = DetailFragmentDirections.actionDetailFragmentToRatingDialog(title)
                    view.findNavController().navigate(action)
                }
            }
            executePendingBindings()
        }
    }

    private fun initToolbarNavigationClickListener() {
        binding.toolbarDetail.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initFragmentResultListener() {
        setFragmentResultListener("rating") { _, bundle ->
            viewModel.insertRating(bundle.getFloat("rating"))
            Snackbar.make(binding.root, "Star ratings are saved.", Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
