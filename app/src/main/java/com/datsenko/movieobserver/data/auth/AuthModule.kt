package com.datsenko.movieobserver.data.auth

import com.datsenko.movieobserver.domain.auth.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
interface AuthModule {

    @Binds
    fun AuthEncryptedStorage.bindStorage(): AuthEncryptedStorageApi

    @Binds
    fun AuthRepositoryImpl.bindAuthRepository(): AuthRepository

    companion object {

        @Singleton
        @Provides
        fun provideAuthService(retrofit: Retrofit): AuthService =
            retrofit.create(AuthService::class.java)
    }
}
