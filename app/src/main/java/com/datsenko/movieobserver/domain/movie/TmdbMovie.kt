package com.datsenko.movieobserver.domain.movie

import java.util.Date

data class TmdbMovie(
    val id: Int,
    val title: String,
    val overview: String,
    val releaseDate: Date,
    val originalTitle: String,
    val posterPath: String,
    val voteAverage: Float,
    val voteCount: Int
)
