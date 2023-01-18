package com.example.pirates_challenge.di

import android.content.Context
import com.example.pirates_challenge.domain.interactor.ForecastInteractor
import com.example.pirates_challenge.domain.interactor.ForecastInteractorImpl
import com.example.pirates_challenge.domain.mapper.DailyForecastMapper
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module(
    includes = [
        DomainModule.DependenciesModule::class
    ]
)
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun bindForecastMapper(@ApplicationContext context: Context): DailyForecastMapper{
        return DailyForecastMapper(context)
    }

    @Module
    @InstallIn(ViewModelComponent::class)
    abstract class DependenciesModule {

        @Binds
        abstract fun bindForecastInteractor(
            impl: ForecastInteractorImpl
        ): ForecastInteractor

    }
}