package com.kimym.marvel.domain.repository

import com.kimym.marvel.domain.model.Appearance
import kotlinx.coroutines.flow.Flow

interface AppearanceRepository {
    val appearance: Flow<Appearance>

    suspend fun updateAppearance(appearance: Appearance)
}
