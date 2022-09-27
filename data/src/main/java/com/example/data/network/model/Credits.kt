package com.example.data.network.model

import com.example.domain.model.CreditsItem

data class Credits(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val id: Int
)
