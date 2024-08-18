package com.kimym.marvel.feature.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kimym.marvel.domain.model.Appearance
import com.kimym.marvel.domain.repository.AppearanceRepository
import com.kimym.marvel.domain.usecase.GetAppearanceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    getAppearanceUseCase: GetAppearanceUseCase,
    private val repository: AppearanceRepository
) : ViewModel() {
    val appearance = getAppearanceUseCase()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), Appearance.FOLLOW_SYSTEM)

    fun setAppearance(appearance: Appearance) {
        viewModelScope.launch {
            repository.setAppearance(appearance)
        }
    }
}
