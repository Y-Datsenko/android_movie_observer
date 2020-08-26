package com.datsenko.movieobserver.data.auth

interface AuthEncryptedStorageApi {

    fun saveGuestSessionId(id: String)

    fun getGuestSessionId(): String?

    fun saveMemberSessionId(id: String)

    fun getMemberSessionId(): String?

    fun updateUserIsGuest(isGuest: Boolean)

    fun isGuestUser(): Boolean
}
