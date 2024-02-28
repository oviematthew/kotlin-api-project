package com.example.kotlin_api_project.model

import com.google.gson.annotations.SerializedName
data class EventLocation(
    @SerializedName("address1") val address1: String,
    @SerializedName("address2") val address2: String?,
    @SerializedName("address3") val address3: String?,
    @SerializedName("city") val city: String,
    @SerializedName("zip_code") val zipCode: String,
    @SerializedName("country") val country: String,
    @SerializedName("state") val state: String,
    @SerializedName("display_address") val displayAddress: List<String>,
    @SerializedName("cross_streets") val crossStreets: String?
)