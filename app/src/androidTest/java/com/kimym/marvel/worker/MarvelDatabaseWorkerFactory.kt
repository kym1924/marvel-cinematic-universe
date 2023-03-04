package com.kimym.marvel.worker

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.kimym.marvel.database.MarvelDao

class MarvelDatabaseWorkerFactory(private val dao: MarvelDao) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker {
        return MarvelDatabaseWorker(appContext, workerParameters, dao)
    }
}
