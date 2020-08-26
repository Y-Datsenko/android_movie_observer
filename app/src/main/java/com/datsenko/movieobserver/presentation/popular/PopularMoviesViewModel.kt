package com.datsenko.movieobserver.presentation.popular

import androidx.hilt.lifecycle.ViewModelInject
import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import com.datsenko.movieobserver.domain.movie.MovieRepository
import timber.log.Timber

class PopularMoviesViewModel @ViewModelInject constructor(
    private val movieRepository: MovieRepository
) :
    RainbowCakeViewModel<PopularMoviesViewState>(PopularMoviesViewState.Initial) {

    fun onViewStarted() {
        if (viewState == PopularMoviesViewState.Initial) {
            loadMovies()
        }
    }

    private fun loadMovies() = execute {
        val movies = movieRepository.getPopularMovies()
        viewState = PopularMoviesViewState.ListReady(data = movies)
        Timber.d("logTag $movies")
    }
}
