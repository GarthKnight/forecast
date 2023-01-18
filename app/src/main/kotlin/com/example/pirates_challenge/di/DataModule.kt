package com.example.pirates_challenge.di

import com.example.pirates_challenge.data.api.ApiService
import com.example.pirates_challenge.domain.repository.DailyForecastRepository
import com.example.pirates_challenge.data.repository.forecast.DailyForecastRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DataModule {

    @Provides
    fun provideDailyForecastRepository(apiService: ApiService): DailyForecastRepository {
        return DailyForecastRepositoryImpl(apiService)
    }


}