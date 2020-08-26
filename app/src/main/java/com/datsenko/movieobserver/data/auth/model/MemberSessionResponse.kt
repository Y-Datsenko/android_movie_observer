package com.datsenko.movieobserver.data.auth.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MemberSessionResponse(
    @Json(name = "success") val success: Boolean,
    @Json(name = "session_id") val sessionId: String
)
