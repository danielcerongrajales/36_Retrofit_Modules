package com.example.data.network.model

data class ProductionCompany(
    val id: Int,
    val logo_path: String?="empty",
    val name: String,
    val origin_country: String
)