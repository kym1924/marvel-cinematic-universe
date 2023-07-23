package com.kimym.marvel.core.datastore

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

private val Context.datastore by preferencesDataStore("marvel_data_store")

class MarvelDatastore @Inject constructor(
    @ApplicationContext private val context: Context
)
