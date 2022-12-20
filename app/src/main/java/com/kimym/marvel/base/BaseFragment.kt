package com.kimym.marvel.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<V : ViewDataBinding>(
    @LayoutRes private val layoutId: Int,
) : Fragment() {
    private var _binding: V? = null
    protected val binding get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return DataBindingUtil.inflate<V>(inflater, layoutId, container, false)
            .also { _binding = it }
            .apply { lifecycleOwner = viewLifecycleOwner }
            .root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
