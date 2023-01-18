package com.example.pirates_challenge.di

import com.example.pirates_challenge.domain.repository.CurrentWeatherRepository
import com.example.pirates_challenge.data.repository.weather.CurrentWeatherRepositoryImpl
import com.example.pirates_challenge.domain.interactor.CurrentWeatherInteractor
import com.example.pirates_challenge.domain.interactor.CurrentWeatherInteractorImpl
import com.example.pirates_challenge.domain.mapper.CurrentWeatherMapper
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
    fun bindWeatherMapper(): CurrentWeatherMapper {
        return CurrentWeatherMapper()
    }

    @Module
    @InstallIn(SingletonComponent::class)
    abstract class DependenciesModule {

        @Binds
        @Singleton
        abstract fun BindsWeatherInteractor(
            impl: CurrentWeatherInteractorImpl
        ): CurrentWeatherInteractor

        @Binds
        @Singleton
        abstract fun provideWeatherRepository(impl: CurrentWeatherRepositoryImpl): CurrentWeatherRepository

    }
}