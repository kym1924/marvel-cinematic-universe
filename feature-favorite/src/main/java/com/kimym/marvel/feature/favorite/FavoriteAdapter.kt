package com.kimym.marvel.feature.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.kimym.marvel.core.model.MovieAndRating
import com.kimym.marvel.core.ui.recyclerview.BaseDiffUtilItemCallback
import com.kimym.marvel.core.ui.recyclerview.BaseViewHolder
import com.kimym.marvel.feature.favorite.databinding.ItemFavoriteBinding

class FavoriteAdapter :
    ListAdapter<MovieAndRating, FavoriteAdapter.FavoriteViewHolder<ItemFavoriteBinding>>(
        BaseDiffUtilItemCallback(
            areItemsSame = { oldItem, newItem -> oldItem.id == newItem.id },
            areContentsSame = { oldItem, newItem -> oldItem == newItem }
        )
    ) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteViewHolder<ItemFavoriteBinding> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFavoriteBinding.inflate(inflater, parent, false)
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: FavoriteViewHolder<ItemFavoriteBinding>,
        position: Int
    ) {
        holder.bind(getItem(position))
    }

    class FavoriteViewHolder<T : ViewDataBinding>(
        binding: T
    ) : BaseViewHolder<T>(binding) {
        fun bind(model: MovieAndRating) {
            with(binding) {
                setVariable(BR.model, model)
                setVariable(BR.callback, callback)
                executePendingBindings()
            }
        }
    }
}
