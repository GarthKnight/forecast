package com.example.pirates_challenge.domain.model.forecast

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Temperature(
    val day: Double,
    val night: Double,
    val eve: Double,
    val morn: Double
) : Parcelable