package com.example.cryptoproject.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptoproject.models.Asset
import com.example.cryptoproject.services.CoinCapApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AssetsListViewModel @Inject constructor(
    private val apiService: CoinCapApiService
): ViewModel() {
    private val _assets = MutableStateFlow<List<Asset>>(emptyList())
    val assets: StateFlow<List<Asset>> = _assets

    init {
        fetchAssets()
    }

    private fun fetchAssets() {
        viewModelScope.launch {
            try {
                val result = apiService.getAssets().data
                val mappedAssets = result.map { assetResponse ->
//                    val price = assetResponse.priceUsd.format("%.2f").toDouble()
                    val percentage = String.format("%.2f", assetResponse.changePercent24Hr.toDouble()).toDouble()
                    val price = String.format("%.2f", assetResponse.priceUsd.toDouble())

                    Asset(
                        assetResponse.id,
                        assetResponse.name,
                        assetResponse.symbol,
                        price,
                        percentage
                    )
                }
                _assets.value = mappedAssets
            } catch (e: Exception) {
                //TODO: HANDLE ERROR
            }
        }
    }
}