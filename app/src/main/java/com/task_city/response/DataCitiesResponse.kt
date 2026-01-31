package com.task_city.response


import com.google.gson.annotations.SerializedName
import com.task_city.entity.DistrictModel

data class DataCitiesResponse(
    @SerializedName("cityCode")
    val cityCode: String = "",
    @SerializedName("cityId")
    val cityId: String = "",
    @SerializedName("cityName")
    val cityName: String = "",
    @SerializedName("cityOtherName")
    val cityOtherName: String = "",
    @SerializedName("districts")
    val districtModel: List<DistrictModel> = listOf(),
    @SerializedName("dropOffAvailability")
    val dropOffAvailability: Boolean = true,
    @SerializedName("pickupAvailability")
    val pickupAvailability: Boolean = true
)