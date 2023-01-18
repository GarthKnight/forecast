package com.example.pirates_challenge.data.repository.location

import android.annotation.SuppressLint
import android.location.Location
import android.os.Looper
import com.example.pirates_challenge.common.exceptions.FailedFetchLocationException
import com.example.pirates_challenge.domain.repository.LocationRepository
import com.google.android.gms.location.*
import kotlinx.coroutines.*
import java.util.*
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


class LocationRepositoryImpl @Inject constructor(
    private val fusedLocationClient: FusedLocationProviderClient
) : LocationRepository {

    @SuppressLint("MissingPermission")
    override suspend fun getCurrentLocation(): Location {
        return suspendCoroutine { continuation ->
            val locationCallback = object : LocationCallback() {
                override fun onLocationResult(result: LocationResult) {
                    super.onLocationResult(result)
                    result.lastLocation?.let {
                        continuation.resume(it)
                        fusedLocationClient.removeLocationUpdates(this)
                    } ?: run {
                        continuation.resumeWithException(FailedFetchLocationException())
                        fusedLocationClient.removeLocationUpdates(this)
                    }
                }
            }
            val locationRequest = LocationRequest.Builder(
                Priority.PRIORITY_HIGH_ACCURACY, 5000
            ).setWaitForAccurateLocation(false)
                .setMinUpdateIntervalMillis(5000)
                .setMaxUpdateDelayMillis(5000)
                .build()

            fusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.getMainLooper()
            )
        }
    }
}