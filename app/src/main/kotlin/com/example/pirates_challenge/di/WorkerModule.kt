package com.example.pirates_challenge.di

import android.content.Context
import androidx.work.WorkManager
import com.example.pirates_challenge.domain.repository.CurrentWeatherRepository
import com.example.pirates_challenge.data.repository.weather.CurrentWeatherRepositoryImpl
import com.example.pirates_challenge.domain.interactor.CurrentWeatherInteractor
import com.example.pirates_challenge.domain.interactor.CurrentWeatherInteractorImpl
import com.example.pirates_challenge.domain.mapper.CurrentWeatherMapper
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module(
    includes = [
        WorkerModule.DependenciesModule::class
    ]
)
class WorkerModule {

    @Provides
    @Singleton
    fun provideWeatherMapper(): CurrentWeatherMapper {
        return CurrentWeatherMapper()
    }

    @Provides
    @Singleton
    fun provideWorkManager(@ApplicationContext context: Context): WorkManager {
        return WorkManager.getInstance(context)
    }

    @Module
    @InstallIn(SingletonComponent::class)
    abstract class DependenciesModule {

        @Binds
        @Singleton
        abstract fun bindsWeatherInteractor(
            impl: CurrentWeatherInteractorImpl
        ): CurrentWeatherInteractor

        @Binds
        @Singleton
        abstract fun provideWeatherRepository(impl: CurrentWeatherRepositoryImpl): CurrentWeatherRepository

    }
}