package com.example.pirates_challenge.data.repository.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import com.example.pirates_challenge.common.exceptions.FailedFetchLocationException
import com.example.pirates_challenge.domain.repository.LocationRepository
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.*
import java.util.*
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


class LocationRepositoryImpl @Inject constructor(
    @ApplicationContext val context: Context
) : LocationRepository {

    private var fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    @SuppressLint("MissingPermission")
    override suspend fun getCurrentLocation(): Location {
        return suspendCoroutine { continuation ->
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    location?.let {
                        continuation.resume(it)
                    } ?: run {
                        continuation.resumeWithException(FailedFetchLocationException())
                    }
                }
                .addOnCanceledListener {
                    continuation.resumeWithException(FailedFetchLocationException())
                }
                .addOnFailureListener {
                    continuation.resumeWithException(it)
                }
        }
    }

}