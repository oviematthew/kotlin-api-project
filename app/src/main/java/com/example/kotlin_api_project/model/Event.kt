package com.example.kotlin_api_project.model

import com.google.gson.annotations.SerializedName

data class Event(
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("attending_count") val attendingCount: Int,
    @SerializedName("category") val category: String,
    @SerializedName("cost") val cost: Double?,
    @SerializedName("cost_max") val costMax: Double?,
    @SerializedName("event_site_url") val eventSiteUrl: String,
    @SerializedName("id") val id: String,
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("interested_count") val interestedCount: Int,
    @SerializedName("is_canceled") val isCanceled: Boolean,
    @SerializedName("is_free") val isFree: Boolean,
    @SerializedName("is_official") val isOfficial: Boolean,
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("longitude") val longitude: Double,
    @SerializedName("tickets_url") val ticketsUrl: String?,
    @SerializedName("time_end") val timeEnd: String?,
    @SerializedName("time_start") val timeStart: String,
    @SerializedName("location") val location: EventLocation,
    @SerializedName("business_id") val businessId: String
)
