package com.example.data.extensions

import com.example.a36_retrofit.data.network.model.ProductionCountry
import com.example.a36_retrofit.data.network.model.SpokenLanguage
import com.example.data.database.entities.MovieDetailsEntity
import com.example.data.database.entities.MovieEntity
import com.example.data.network.model.*
import com.example.domain.model.*


fun MovieDetailsItem.toDatabase() = MovieDetailsEntity(
    adult,
    backdrop_path,
// belongs_to_collection,
    budget,
// genres,
    homepage,
    id,
    imdb_id,
    original_language,
    original_title,
    overview,
    popularity,
    poster_path,
// production_companies,
// production_countries,
    release_date,
    revenue,
    runtime,
// spoken_languages,
    status,
    tagline,
    title,
    video,
    vote_average,
    vote_count
)

fun MovieDetailsEntity.toDomain() = MovieDetailsItem(
    adult = adult,
    backdrop_path = backdrop_path,
//    belongs_to_collection=belongs_to_collection,
    budget = budget,
//    genres,
    homepage = homepage,
    id = id,
    imdb_id = imdb_id,
    original_language = original_language,
    original_title = original_title,
    overview = overview,
    popularity = popularity,
    poster_path = poster_path,
//    production_companies,
//    production_countries,
    release_date = release_date,
    revenue = revenue,
    runtime = runtime,
//    spoken_languages,
    status = status,
    tagline = tagline,
    title = title,
    video = video,
    vote_average = vote_average,
    vote_count = vote_count
)


fun MovieItem.toDatabase() = MovieEntity(
    adult,
    backdrop_path,
    genre_ids,
    id,
    original_language,
    original_title,
    overview,
    popularity,
    poster_path,
    release_date,
    title,
    video,
    vote_average,
    vote_count
)

fun MovieEntity.toDomain() = MovieItem(
    adult = adult,
    backdrop_path = backdrop_path,
    genre_ids = genre_ids,
    id = id,
    original_language = original_language,
    original_title = original_title,
    overview = overview,
    popularity = popularity,
    poster_path = poster_path,
    release_date = release_date,
    title = title,
    video = video,
    vote_average = vote_average,
    vote_count = vote_count
)

fun Cast.toDomain() = CastItem(
    adult,
    cast_id,
    character,
    credit_id,
    gender,
    id,
    known_for_department,
    name,
    order,
    original_name,
    popularity,
    profile_path

)

fun Credits.toDomain() = CreditsItem(
    cast = cast.map { it.toDomain() },
    crew = crew.map { it.toDomain() },
    id = id

)

fun Crew.toDomain() = CrewItem(
    adult,
    credit_id,
    department,
    gender,
    id,
    job,
    known_for_department,
    name,
    original_name,
    popularity,
    profile_path

)

fun MovieById.toDomain() = MovieDetailsItem(
    adult,
    backdrop_path,
    belongs_to_collection?.toDomain(),
    budget,
    genres.map { it.toDomain() },
    homepage,
    id,
    imdb_id,
    original_language,
    original_title,
    overview,
    popularity,
    poster_path,
    production_companies.map { it.toDomain() },
    production_countries.map { it.toDomain() },
    release_date,
    revenue,
    runtime,
    spoken_languages.map { it.toDomain() },
    status,
    tagline,
    title,
    video,
    vote_average,
    vote_count
)

fun BelongsToCollection.toDomain() = BelongsToCollectionItem(
    backdrop_path,
    id,
    name,
    poster_path

)

fun Genre.toDomain() = GenreItem(
    id,
    name
)

fun ProductionCompany.toDomain() = ProductionCompanyItem(
    id,
    logo_path,
    name,
    origin_country
)

fun ProductionCountry.toDomain() = ProductionCountryItem(
    iso_3166_1,
    name
)


fun SpokenLanguage.toDomain() = SpokenLanguageItem(
    english_name,
    iso_639_1,
    name
)

fun MovieResultModel.toDomain() = MovieItem(
    adult,
    backdrop_path,
    genre_ids,
    id,
    original_language,
    original_title,
    overview,
    popularity,
    poster_path,
    release_date,
    title,
    video,
    vote_average,
    vote_count
)