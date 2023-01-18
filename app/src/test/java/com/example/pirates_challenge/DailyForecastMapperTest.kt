package com.example.pirates_challenge

import android.content.Context
import com.example.pirates_challenge.data.model.NWDailyForecast
import com.example.pirates_challenge.domain.mapper.DailyForecastMapper
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class DailyForecastMapperTest {

    //I used here relaxed mockk only because this is test assignment.
    //In production development we should create proper object before tests

    private val context = mockk<Context>(relaxed = true)
    private val forecast = mockk<NWDailyForecast>(relaxed = true)
    private val forecastInfo = mockk<NWDailyForecast.NWForecastInfo>(relaxed = true)

    private val mapper = DailyForecastMapper(context)

    @Before
    fun setup() {
        every { context.getString(any()) } returns "hot"
        every { forecast.list } returns listOf(forecastInfo, forecastInfo, forecastInfo)
        every { forecastInfo.weather } returns listOf(mockk(relaxed = true))
    }

    @Test
    fun checkIfListSizeCorrect() {
        val result = mapper.invoke(forecast)

        assertEquals(forecast.list.size, result.size)
    }

    @Test
    fun checkIfCityNameCorrect() {
        val targetCityName = "London"

        every { forecast.city.name } returns targetCityName

        val result = mapper.invoke(forecast)

        assertEquals(result.first().city, targetCityName)
    }
}