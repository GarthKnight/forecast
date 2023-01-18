package com.example.pirates_challenge.domain.repository

import android.location.Location


interface LocationRepository {

    suspend fun getCurrentLocation(): Location
}