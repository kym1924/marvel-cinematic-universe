package com.kimym.marvel.domain.usecase

import com.kimym.marvel.domain.model.Appearance
import com.kimym.marvel.domain.repository.AppearanceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAppearanceUseCase @Inject constructor(
    private val repository: AppearanceRepository
) {
    operator fun invoke(): Flow<Appearance> {
        return repository.appearance
    }
}
