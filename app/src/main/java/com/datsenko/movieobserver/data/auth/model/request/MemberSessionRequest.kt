package com.datsenko.movieobserver.data.auth.model.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MemberSessionRequest(
    @Json(name = "request_token") val requestToken: String
)
