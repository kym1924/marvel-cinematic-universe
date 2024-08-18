package com.kimym.marvel.domain.usecase

import com.kimym.marvel.domain.model.Appearance
import com.kimym.marvel.domain.repository.AppearanceRepository
import javax.inject.Inject

class UpdateAppearanceUseCase @Inject constructor(
    private val repository: AppearanceRepository
) {
    suspend operator fun invoke(appearance: Appearance) {
        repository.updateAppearance(appearance)
    }
}
