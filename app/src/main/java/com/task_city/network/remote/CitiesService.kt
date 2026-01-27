package com.task_city.network.remote

import com.task_city.response.CitiesResponse
import com.task_city.utils.Constant.END_POINT
import retrofit2.http.GET
import retrofit2.http.Query

interface CitiesService {
    @GET(END_POINT)
    suspend fun getCities(
        @Query("countryId") countryID: String
    ): CitiesResponse

}