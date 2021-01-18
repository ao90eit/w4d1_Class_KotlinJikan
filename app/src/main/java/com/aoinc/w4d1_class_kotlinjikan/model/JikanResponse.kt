package com.aoinc.w4d1_class_kotlinjikan.model

import com.google.gson.annotations.SerializedName

data class JikanResponse(
    val last_page: Int,
    val request_cache_expiry: Int,
    val request_cached: Boolean,
    val request_hash: String,
    @SerializedName("results")
    val jikanResults: List<JikanResult>
)