package com.kimym.marvel.domain.repository

import com.kimym.marvel.domain.model.Appearance
import kotlinx.coroutines.flow.Flow

interface AppearanceRepository {
    fun getAppearance(): Flow<Appearance>

    suspend fun setAppearance(appearance: Appearance)
}
