package com.example.pirates_challenge

import android.location.Location
import com.example.pirates_challenge.data.repository.forecast.DailyForecastRepositoryImpl
import com.example.pirates_challenge.data.repository.location.LocationRepositoryImpl
import com.example.pirates_challenge.domain.interactor.ForecastInteractorImpl
import com.example.pirates_challenge.domain.mapper.DailyForecastMapper
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class ForecastInteractorUnitTest {

    private val forecastRepository = mockk<DailyForecastRepositoryImpl>()
    private val locationRepository = mockk<LocationRepositoryImpl>()
    private val mapper = mockk<DailyForecastMapper>()

    private var forecastInteractor = ForecastInteractorImpl(
        forecastRepository,
        locationRepository,
        mapper
    )

    @Before
    fun setUp() {
        val location = mockk<Location>()
        every { location.latitude } returns 10.10
        every { location.longitude } returns 10.10

        coEvery { locationRepository.getCurrentLocation() } returns location
        coEvery { forecastRepository.getDailyForecast(location) } returns mockk()
        every { mapper.invoke(any()) } returns mockk()

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getDailyForecast_isCorrect() = runTest {
        forecastInteractor.getDailyForecast()

        coVerify { locationRepository.getCurrentLocation() }
        coVerify { forecastRepository.getDailyForecast(any()) }
    }
}