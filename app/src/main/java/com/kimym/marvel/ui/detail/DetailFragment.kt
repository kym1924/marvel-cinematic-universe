package com.kimym.marvel.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.kimym.marvel.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
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
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initMovieCollect()
        initToolbarNavigationClickListener()
    }

    private fun initMovieCollect() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.movie.collect {
                    binding.movie = it
                    binding.executePendingBindings()
                }
            }
        }
    }

    private fun initToolbarNavigationClickListener() {
        binding.toolbarDetail.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
