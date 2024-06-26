package com.kimym.marvel.core.worker

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.kimym.marvel.core.database.dao.MovieDao

class MarvelDatabasePrePopulateWorkerFactory(private val dao: MovieDao) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker {
        return MarvelDatabasePrePopulateWorker(appContext, workerParameters, dao)
    }
}
