package com.datsenko.movieobserver.presentation.popular

sealed class PopularMoviesViewState {

    object Initial : PopularMoviesViewState()

    class ListReady(
        val data: List<Any>
    ) : PopularMoviesViewState()
}
