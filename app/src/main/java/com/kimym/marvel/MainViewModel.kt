package com.kimym.marvel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kimym.marvel.domain.usecase.GetAppearanceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    getAppearanceUseCase: GetAppearanceUseCase
) : ViewModel() {
    val appearance = getAppearanceUseCase()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), null)
}
