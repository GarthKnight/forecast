package com.example.pirates_challenge.data.model

import com.google.gson.annotations.SerializedName

class NWDailyForecast(
    @SerializedName("city") val city: NWCity,
    @SerializedName("list") val list: List<NWForecastInfo>
) {
    class NWCity(
        @SerializedName("id") val id: Int,
        @SerializedName("name") val name: String,
        @SerializedName("country") val country: String
    )

    class NWForecastInfo(
        @SerializedName("dt") val date: Long,
        @SerializedName("sunset") val sunset: Long,
        @SerializedName("sunrise") val sunrise: Long,
        @SerializedName("temp") val temperature: NWTemperature,
        @SerializedName("feels_like") val feel: NWTemperature,
        @SerializedName("weather") val weather: List<NWWeather>
    )

    class NWTemperature(
        @SerializedName("day") val day: Double,
        @SerializedName("min") val min: Double?,
        @SerializedName("max") val max: Double?,
        @SerializedName("night") val night: Double,
        @SerializedName("eve") val eve: Double,
        @SerializedName("morn") val morn: Double
    )
}


