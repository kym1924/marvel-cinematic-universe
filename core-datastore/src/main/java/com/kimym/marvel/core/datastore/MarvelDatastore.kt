package com.kimym.marvel.core.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.datastore by preferencesDataStore("marvel_store")

class MarvelDatastore @Inject constructor(@ApplicationContext context: Context) {
    private val datastore = context.datastore

    fun getAppearance(): Flow<Int?> {
        return datastore.data.map { preferences ->
            preferences[appearance]
        }
    }

    suspend fun setAppearance(value: Int) {
        datastore.edit { preferences ->
            preferences[appearance] = value
        }
    }

    companion object {
        private val appearance = intPreferencesKey("appearance_key")
    }
}
