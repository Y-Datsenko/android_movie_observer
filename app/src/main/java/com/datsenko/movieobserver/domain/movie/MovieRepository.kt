package com.datsenko.movieobserver.domain.movie

interface MovieRepository {

    suspend fun getPopularMovies(): List<TmdbMovie>
}
