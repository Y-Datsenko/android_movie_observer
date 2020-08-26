package com.datsenko.movieobserver.data.encrypted

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Encrypted

@Module
@InstallIn(ApplicationComponent::class)
object EncryptedModule {

    @Encrypted
    @Provides
    @Singleton
    fun provideEncryptedSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        val masterKeyBuilder = MasterKey.Builder(context).apply {
            setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        }
        return EncryptedSharedPreferences.create(
            context,
            "safe_shared_prefs",
            masterKeyBuilder.build(),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }
}
