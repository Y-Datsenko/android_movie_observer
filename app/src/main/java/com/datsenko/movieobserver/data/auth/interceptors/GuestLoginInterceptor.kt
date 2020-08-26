package com.datsenko.movieobserver.data.auth.interceptors

import android.os.Looper
import com.datsenko.movieobserver.data.auth.AuthEncryptedStorageApi
import com.datsenko.movieobserver.data.auth.model.GuestSessionResponse
import com.squareup.moshi.Moshi
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber
import javax.inject.Inject

class GuestLoginInterceptor @Inject constructor(
    private val authEncryptedStorage: AuthEncryptedStorageApi,
    private val moshi: Moshi
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()
        if (authEncryptedStorage.isGuestUser() && authEncryptedStorage.getGuestSessionId() == null) {
            val loginBuilder = HttpUrl.Builder()
                .scheme(request.url.scheme)
                .host(request.url.host)
                .addPathSegment("3")
                .addPathSegment("authentication")
                .addPathSegment("guest_session")
                .addPathSegment("new")
                .build()
            builder.url(loginBuilder)
            val response = chain.proceed(builder.build())
            if (response.isSuccessful) {
                val guestSesResp =
                    response.body?.let { moshi.adapter(GuestSessionResponse::class.java).fromJson(it.string()) }
                guestSesResp?.sessionId?.let(authEncryptedStorage::saveGuestSessionId)
            } else response.close()
        }
        Timber.d("logTag finished session ${authEncryptedStorage.getGuestSessionId()}")
        return chain.proceed(request)
    }
}
