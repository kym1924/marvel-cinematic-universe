package com.kimym.marvel.core.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.kimym.marvel.core.database.dao.MovieDao
import com.kimym.marvel.core.database.entity.MovieEntity
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.coroutineScope

@HiltWorker
class MarvelDatabasePrePopulateWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val dao: MovieDao
) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        return coroutineScope {
            try {
                applicationContext.assets.open("marvel.json").use { inputStream ->
                    JsonReader(inputStream.reader()).use { jsonReader ->
                        val type = object : TypeToken<List<MovieEntity>>() {}.type
                        val list: List<MovieEntity> = Gson().fromJson(jsonReader, type)
                        dao.insertMovies(movies = list)
                    }
                }
                Result.success()
            } catch (e: Exception) {
                Result.failure()
            }
        }
    }
}
