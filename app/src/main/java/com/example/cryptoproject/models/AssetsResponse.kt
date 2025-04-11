package com.example.cryptoproject.models

import kotlinx.serialization.Serializable

@Serializable
data class AssetsResponse(
    val data: List<AssetResponse>
)