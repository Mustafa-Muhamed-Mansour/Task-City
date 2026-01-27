package com.task_city.di

import com.task_city.network.remote.CitiesService
import com.task_city.repository.CitiesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule() {
    // Provide ViewModel dependencies here using Hilt annotations
    @Provides
    @Singleton
    fun provideCitiesRepository(citiesService: CitiesService): CitiesRepository {
        return CitiesRepository(citiesService = citiesService)
    }
}