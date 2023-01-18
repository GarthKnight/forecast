package com.example.pirates_challenge.domain.mapper

import android.content.Context
import com.example.pirates_challenge.R
import com.example.pirates_challenge.common.ImageUtils.createIconUri
import com.example.pirates_challenge.data.model.NWDailyForecast
import com.example.pirates_challenge.domain.model.forecast.DailyForecast
import com.example.pirates_challenge.domain.model.forecast.Temperature
import dagger.hilt.android.qualifiers.ApplicationContext
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class DailyForecastMapper @Inject constructor(
    @ApplicationContext private val context: Context
) {

    fun invoke(forecast: NWDailyForecast): List<DailyForecast> = with(forecast) {
        val city = city.name
        val dateFormat = SimpleDateFormat("MMMM dd", Locale.US)
        val weekFormat = SimpleDateFormat("EEE", Locale.US)

        return this.list.mapIndexed { index, info ->

            val temp = Temperature(
                morn = info.temperature.morn,
                day = info.temperature.day,
                eve = info.temperature.eve,
                night = info.temperature.night
            )

            val feel = Temperature(
                morn = info.feel.morn,
                day = info.feel.day,
                eve = info.feel.eve,
                night = info.feel.night
            )

            val weather = info.weather[0].main
            val weatherDescription = info.weather[0].description
            val icon = createIconUri(info.weather.first().icon)
            val date = info.date * 1000
            val readableDate = dateFormat.format(Date(date))
            val dayOfWeek = when (index) {
                0 -> "Today"
                1 -> "Tomorrow"
                else -> weekFormat.format(date)

            }
            val tempDescription = when {
                temp.day > 25.0 -> context.getString(R.string.hot_text)
                temp.day < 10.0 -> context.getString(R.string.cold_text)
                else -> ""
            }

            DailyForecast(
                date = date,
                readableDate = readableDate,
                dayOfWeek = dayOfWeek,
                city = city,
                temperature = temp,
                feelsTemperature = feel,
                tempDescription = tempDescription,
                weather = weather,
                weatherDescription = weatherDescription,
                iconUrl = icon
            )
        }
    }
}