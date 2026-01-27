package com.task_city.response


import com.google.gson.annotations.SerializedName

data class CitiesResponse(
    @SerializedName("data")
    val dataCitiesResponse: List<DataCitiesResponse> = listOf(),
    @SerializedName("message")
    val message: String = "",
    @SerializedName("success")
    val success: Boolean = true
)