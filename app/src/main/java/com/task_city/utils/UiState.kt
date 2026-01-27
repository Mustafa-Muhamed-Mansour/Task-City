package com.task_city.utils

import com.task_city.response.CitiesResponse

data class CitiesState(
    val isLoading: Boolean = false,
    val error: String = "",
    val data: CitiesResponse? = null
)
