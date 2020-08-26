package com.datsenko.movieobserver.data.movie

import com.datsenko.movieobserver.data.movie.model.TmdbMovieResponse
import com.datsenko.movieobserver.domain.movie.TmdbMovie

fun TmdbMovieResponse.toDomain(): TmdbMovie =
    TmdbMovie(
        id = id,
        voteAverage = voteAverage,
        title = title,
        overview = overview,
        releaseDate = releaseDate,
        originalTitle = originalTitle,
        posterPath = posterPath,
        voteCount = voteCount
    )
