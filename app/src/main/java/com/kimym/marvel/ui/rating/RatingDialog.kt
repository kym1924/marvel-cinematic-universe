package com.kimym.marvel.ui.rating

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.kimym.marvel.databinding.DialogRatingBinding

class RatingDialog : DialogFragment() {
    private var _binding: DialogRatingBinding? = null
    private val binding get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogRatingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initMovieTitle()
        initRatingBarChangeListener()
    }

    private fun initMovieTitle() {
        val args by navArgs<RatingDialogArgs>()
        binding.tvRatingMovieTitle.text = args.title
        binding.executePendingBindings()
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
