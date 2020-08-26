package com.datsenko.movieobserver.data.movie.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.Date

@JsonClass(generateAdapter = true)
data class TmdbMovieResponse(
    @Json(name = "id") val id: Int,
    @Json(name = "title") val title: String,
    @Json(name = "overview") val overview: String,
    @Json(name = "release_date") val releaseDate: Date,
    @Json(name = "original_title") val originalTitle: String,
    @Json(name = "poster_path") val posterPath: String,
    @Json(name = "vote_average") val voteAverage: Float,
    @Json(name = "vote_count") val voteCount: Int
)

@JsonClass(generateAdapter = true)
data class TmdbMoviesResponse(
    @Json(name = "results") val results: List<TmdbMovieResponse>
)
