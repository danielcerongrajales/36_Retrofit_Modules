package com.example.domain.model

data class ProductionCompanyItem(
    val id: Int,
    val logo_path: String?="empty",
    val name: String,
    val origin_country: String
)