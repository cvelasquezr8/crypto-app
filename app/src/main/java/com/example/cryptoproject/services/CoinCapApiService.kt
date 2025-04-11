package com.example.cryptoproject.services

import com.example.cryptoproject.models.Asset
import com.example.cryptoproject.models.AssetsResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import javax.inject.Inject

class CoinCapApiService @Inject constructor(
    private val client: HttpClient
) {

    suspend fun getAssets(): AssetsResponse {
        val response: HttpResponse = client.get( urlString = "https://6f75bb0d-40ea-43fd-b044-ae9195937770.mock.pstmn.io/v3/assets?apiKey=5a9b50672c792f425d5ec307ee11db8c2c5661116baca3b84cc32d0eaa7ae51e")
        return response.body()
    }
}