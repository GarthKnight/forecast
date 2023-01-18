package com.example.pirates_challenge.data.model

import com.google.gson.annotations.SerializedName

class NWCurrentWeather(
    @SerializedName("weather") val weather: List<NWWeather>,
    @SerializedName("main") val details: NWWeatherDetails

) {

    class NWWeatherDetails(
        @SerializedName("temp") val temp: Double,
        @SerializedName("feels_like") val feelsLike: Double
    )

}