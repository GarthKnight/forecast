package com.example.pirates_challenge.data.model

import com.google.gson.annotations.SerializedName

class NWWeather(
    @SerializedName("main") val main: String,
    @SerializedName("description") val description: String,
    @SerializedName("icon") val icon: String
)