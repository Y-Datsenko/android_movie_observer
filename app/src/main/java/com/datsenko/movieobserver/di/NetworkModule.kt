package com.datsenko.movieobserver.di

import com.datsenko.movieobserver.BuildConfig
import com.datsenko.movieobserver.data.auth.interceptors.ApiKeyInterceptor
import com.datsenko.movieobserver.data.auth.interceptors.GuestLoginInterceptor
import com.datsenko.movieobserver.data.utils.AppDispatchers
import com.datsenko.movieobserver.data.utils.DefaultDispatchers
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import java.util.Date
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
interface NetworkModule {

    companion object {

        @Singleton
        @Provides
        fun provideHttpClient(
            apiKeyInterceptor: ApiKeyInterceptor,
            loggingInterceptor: HttpLoggingInterceptor,
            guestLoginInterceptor: GuestLoginInterceptor
        ): OkHttpClient =
            OkHttpClient().newBuilder()
                .addInterceptor(guestLoginInterceptor)
                .addInterceptor(apiKeyInterceptor)
                .addInterceptor(loggingInterceptor)
                .build()

        @Singleton
        @Provides
        fun provideRetrofit(
            client: dagger.Lazy<OkHttpClient>,
            moshi: Moshi
        ): Retrofit =
            Retrofit.Builder()
                .callFactory(object : Call.Factory {
                    override fun newCall(request: Request): Call {
                        return client.get().newCall(request)
                    }
                })
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()

        @Provides
        @Singleton
        internal fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
            HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
                override fun log(message: String) {
                    Timber.tag("OkHttp").d(message)
                }
            }).apply {
                level = if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else HttpLoggingInterceptor.Level.NONE
            }

        @Provides
        internal fun provideMoshi(): Moshi =
            Moshi.Builder()
                .add(Date::class.java, Rfc3339DateJsonAdapter())
                .build()
    }

    @Binds
    @Singleton
    fun DefaultDispatchers.bindDispatchers(): AppDispatchers
}
