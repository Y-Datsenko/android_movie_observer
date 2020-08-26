package com.datsenko.movieobserver.data.movie

import com.datsenko.movieobserver.data.movie.model.TmdbMovieResponse
import com.datsenko.movieobserver.data.utils.AppDispatchers
import com.datsenko.movieobserver.domain.movie.MovieRepository
import com.datsenko.movieobserver.domain.movie.TmdbMovie
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val dispatchers: AppDispatchers,
    private val movieService: MovieService
) : MovieRepository {

    override suspend fun getPopularMovies(): List<TmdbMovie> =
        withContext(dispatchers.io) {
            movieService.getPopularMovies().results.map(TmdbMovieResponse::toDomain)
        }
}
