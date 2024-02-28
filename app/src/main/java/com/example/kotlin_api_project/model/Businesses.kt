package com.example.kotlin_api_project.model

data class Businesses(
    val name: String,
    val isClosed: Boolean,
    val image_url: String,
    val rating: Float,
    val location: Location,
    val phone: String
)
