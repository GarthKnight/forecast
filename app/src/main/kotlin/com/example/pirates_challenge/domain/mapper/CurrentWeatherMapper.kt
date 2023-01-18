package com.example.pirates_challenge.domain.mapper

import com.example.pirates_challenge.data.model.NWCurrentWeather
import com.example.pirates_challenge.domain.model.weather.CurrentWeather

class CurrentWeatherMapper {

    fun invoke(currentWeather: NWCurrentWeather): CurrentWeather = with(currentWeather) {
        return CurrentWeather(
            temp = details.temp,
            weather = weather.first().main
        )
    }
}