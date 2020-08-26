package com.datsenko.movieobserver.data.auth

import android.content.SharedPreferences
import androidx.core.content.edit
import com.datsenko.movieobserver.data.encrypted.Encrypted
import dagger.Lazy
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthEncryptedStorage @Inject constructor(
    @Encrypted private val lazyPreferences: Lazy<SharedPreferences>
) : AuthEncryptedStorageApi {

    private companion object {

        const val KEY_GUEST_SESSION = "KEY_GUEST_SESSION"
        const val KEY_MEMBER_SESSION = "KEY_MEMBER_SESSION"
        const val KEY_IS_GUEST_USER = "KEY_IS_GUEST_USER"
    }

    private val sharedPreferences: SharedPreferences
        get() = lazyPreferences.get()

    override fun saveGuestSessionId(id: String) {
        sharedPreferences.edit {
            putString(KEY_GUEST_SESSION, id)
        }
    }

    override fun getGuestSessionId(): String? =
        sharedPreferences.getString(KEY_GUEST_SESSION, null)

    override fun saveMemberSessionId(id: String) {
        sharedPreferences.edit {
            putString(KEY_MEMBER_SESSION, id)
        }
    }

    override fun getMemberSessionId(): String? =
        sharedPreferences.getString(KEY_MEMBER_SESSION, null)

    override fun updateUserIsGuest(isGuest: Boolean) {
        sharedPreferences.edit {
            putBoolean(KEY_IS_GUEST_USER, isGuest)
        }
    }

    override fun isGuestUser(): Boolean =
        sharedPreferences.getBoolean(KEY_IS_GUEST_USER, true)
}
