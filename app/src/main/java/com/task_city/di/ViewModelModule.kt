package com.task_city.di

import com.task_city.repository.CitiesRepository
import com.task_city.view_model.CitiesViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class ViewModelModule {
    // Provide ViewModel dependencies here using Hilt annotations
    @Provides
    fun provideCitiesViewModel(citiesRepository: CitiesRepository): CitiesViewModel {
        return CitiesViewModel(citiesRepository = citiesRepository)
    }
}