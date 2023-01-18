package com.example.pirates_challenge.data.api

import com.example.pirates_challenge.BuildConfig
import com.example.pirates_challenge.data.model.NWCurrentWeather
import com.example.pirates_challenge.data.model.NWDailyForecast
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("forecast/daily")
    suspend fun getDailyForecast(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("cnt") int: Int = 16,
        @Query("appid") apiKey: String = BuildConfig.WEATHER_API_KEY,
        @Query("units") metric: String = "metric"
    ): NWDailyForecast

    @GET("weather")
    suspend fun getCurrentWeatherInfo(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") apiKey: String = BuildConfig.WEATHER_API_KEY,
        @Query("units") metric: String = "metric"
    ): NWCurrentWeather

    companion object {
        private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

        fun create(): ApiService {
            val logger =
                HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

            val client = OkHttpClient.Builder().addInterceptor(logger).build()

            return Retrofit.Builder().baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }

}