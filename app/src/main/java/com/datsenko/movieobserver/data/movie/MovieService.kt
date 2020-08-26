package com.datsenko.movieobserver.data.movie

import com.datsenko.movieobserver.data.movie.model.TmdbMoviesResponse
import retrofit2.http.GET

interface MovieService {
    @GET("movie/popular")
    suspend fun getPopularMovies(): TmdbMoviesResponse
}
