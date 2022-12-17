package com.kimym.marvel.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kimym.marvel.data.repository.DetailRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class DetailViewModel @AssistedInject constructor(
    repository: DetailRepository,
    @Assisted private val title: String
) : ViewModel() {
    val movie = repository.getMovie(title)

    @AssistedFactory
    interface DetailAssistedFactory {
        fun create(title: String): DetailViewModel
    }

    companion object {
        fun provideDetailAssistedFactory(
            assistedFactory: DetailAssistedFactory,
            title: String
        ): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return assistedFactory.create(title) as T
                }
            }
        }
    }
}
