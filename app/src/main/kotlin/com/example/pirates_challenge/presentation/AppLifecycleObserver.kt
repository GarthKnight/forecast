package com.example.pirates_challenge.presentation

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import androidx.work.*
import com.example.pirates_challenge.worker.ForecastWorker
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class AppLifecycleObserver @Inject constructor(
    @ApplicationContext private val context: Context
) : LifecycleEventObserver {


    private val workManager = WorkManager.getInstance(context)

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_START -> {
                workManager.cancelAllWorkByTag(ForecastWorker.TAG)
            }
            Lifecycle.Event.ON_STOP -> {
                val repeatingRequest = PeriodicWorkRequestBuilder<ForecastWorker>(
                    15, TimeUnit.MINUTES
                ).setInitialDelay(15, TimeUnit.MINUTES).build()

                workManager.enqueueUniquePeriodicWork(
                    ForecastWorker.TAG,
                    ExistingPeriodicWorkPolicy.REPLACE,
                    repeatingRequest
                )
            }
            else -> {
                //no-op
            }
        }
    }

}