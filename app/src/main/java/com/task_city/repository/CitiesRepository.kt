package com.task_city.repository

import com.task_city.network.remote.CitiesService
import com.task_city.response.CitiesResponse
import javax.inject.Inject

class CitiesRepository @Inject constructor(
    private val citiesService: CitiesService
) {
    suspend fun getCities(
        countryID: String
    ): Result<CitiesResponse> {
        return try {
            val result = citiesService.getCities(countryID = countryID)
            Result.success(value = result)
        } catch (e: Exception) {
            Result.failure(exception = e)
        }
    }
}