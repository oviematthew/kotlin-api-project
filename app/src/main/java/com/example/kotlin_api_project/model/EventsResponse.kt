package com.example.kotlin_api_project.model

import com.google.gson.annotations.SerializedName
data class EventsResponse(
    @SerializedName("events") val events: List<Event>,
    @SerializedName("total") val total: Int
)
