package com.example.a36_retrofit.data.network.model

import com.example.data.network.model.MovieResultModel

data class MovieTopRated(
    val page: Int?=0,
    val results: List<MovieResultModel>?= emptyList(),
    val total_pages: Int?=0,
    val total_results: Int?=0
)