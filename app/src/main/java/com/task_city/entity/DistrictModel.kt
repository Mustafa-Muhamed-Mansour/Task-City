package com.task_city.entity

import com.google.gson.annotations.SerializedName

data class DistrictModel(
    @SerializedName("coverage")
    val coverage: String = "",
    @SerializedName("districtId")
    val districtId: String = "",
    @SerializedName("districtName")
    val districtName: String = "",
    @SerializedName("districtOtherName")
    val districtOtherName: String = "",
    @SerializedName("dropOffAvailability")
    val dropOffAvailability: Boolean = true,
    @SerializedName("isBusy")
    val isBusy: Boolean = true,
    @SerializedName("notAllowedBulkyOrders")
    val notAllowedBulkyOrders: Boolean = true,
    @SerializedName("pickupAvailability")
    val pickupAvailability: Boolean = true,
    @SerializedName("zoneId")
    val zoneId: String = "",
    @SerializedName("zoneName")
    val zoneName: String = "",
    @SerializedName("zoneOtherName")
    val zoneOtherName: String = ""
)