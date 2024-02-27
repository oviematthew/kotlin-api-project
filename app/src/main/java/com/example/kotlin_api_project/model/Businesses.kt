package com.example.kotlin_api_project.model

data class Businesses(
    val name: String,
    val isClosed: Boolean,
    val imageUrl: String,
    val rating: Float,
    val location: List<Location>,
    val phone: String
)
