package com.example.domain.model


data class MovieDetailsItem(
    val adult: Boolean,
    val backdrop_path: String,
    val belongs_to_collection: BelongsToCollectionItem?=null,
    val budget: Int,
    val genres: List<GenreItem>?=null,
    val homepage: String,
    val id: Int,
    val imdb_id: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val production_companies: List<ProductionCompanyItem>?=null,
    val production_countries: List<ProductionCountryItem>?=null,
    val release_date: String,
    val revenue: Int,
    val runtime: Int,
    val spoken_languages: List<SpokenLanguageItem>?=null,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)


