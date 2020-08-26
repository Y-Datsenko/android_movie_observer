package com.datsenko.movieobserver.data.auth.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RequestTokenResponse(
    @Json(name = "success") val success: Boolean,
    @Json(name = "request_token") val requestToken: String,
    @Json(name = "expires_at") val expiresAt: String
)
