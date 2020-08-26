package com.datsenko.movieobserver.data.movie

import com.datsenko.movieobserver.domain.movie.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
interface MovieModule {

    @Binds
    fun MovieRepositoryImpl.bindMovieRepository(): MovieRepository

    companion object {

        @Singleton
        @Provides
        fun provideMovieService(retrofit: Retrofit): MovieService =
            retrofit.create(MovieService::class.java)
    }
}
