package com.example.pirates_challenge.di

import android.content.Context
import com.example.pirates_challenge.data.api.ApiService
import com.example.pirates_challenge.domain.repository.LocationRepository
import com.example.pirates_challenge.data.repository.location.LocationRepositoryImpl
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return ApiService.create()
    }

    @Provides
    @Singleton
    fun provideLocationProvider(@ApplicationContext context: Context): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(context)
    }

    @Provides
    @Singleton
    fun provideLocationRepository(locationProviderClient: FusedLocationProviderClient): LocationRepository {
        return LocationRepositoryImpl(locationProviderClient)
    }

}