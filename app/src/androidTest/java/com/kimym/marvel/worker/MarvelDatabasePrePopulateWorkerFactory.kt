package com.kimym.marvel.worker

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.kimym.marvel.core.database.MarvelDao
import com.kimym.marvel.core.worker.MarvelDatabasePrePopulateWorker

class MarvelDatabasePrePopulateWorkerFactory(private val dao: MarvelDao) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker {
        return MarvelDatabasePrePopulateWorker(appContext, workerParameters, dao)
    }
}
