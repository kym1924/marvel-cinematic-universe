package com.kimym.marvel.data.repository

import com.kimym.marvel.core.datastore.MarvelDatastore
import com.kimym.marvel.domain.model.Appearance
import com.kimym.marvel.domain.repository.AppearanceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AppearanceRepositoryImpl @Inject constructor(
    private val datastore: MarvelDatastore
) : AppearanceRepository {
    override val appearance: Flow<Appearance> =
        datastore.getAppearance().map { value ->
            when (value) {
                0 -> Appearance.LIGHT
                1 -> Appearance.DARK
                else -> Appearance.FOLLOW_SYSTEM
            }
        }

    override suspend fun updateAppearance(appearance: Appearance) {
        datastore.setAppearance(appearance.value)
    }
}
