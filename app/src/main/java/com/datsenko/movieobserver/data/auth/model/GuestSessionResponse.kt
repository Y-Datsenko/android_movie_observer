package com.datsenko.movieobserver.data.auth.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GuestSessionResponse(
    @Json(name = "success") val success: Boolean,
    @Json(name = "guest_session_id") val sessionId: String,
    @Json(name = "expires_at") val expiresAt: String
)
