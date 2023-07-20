package com.kimym.marvel.feature.rating

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kimym.marvel.feature.rating.databinding.DialogRatingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RatingDialog : DialogFragment() {
    private var _binding: DialogRatingBinding? = null
    private val binding get() = requireNotNull(_binding)
    private val viewModel by viewModels<RatingViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogRatingBinding.inflate(inflater, container, false).apply {
            vm = viewModel
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRatingBarChangeListener()
    }

    private fun initRatingBarChangeListener() {
        binding.ratingBar.setOnRatingBarChangeListener { _, value, _ ->
            setFragmentResult("rating", bundleOf("rating" to value))
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
