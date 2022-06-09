package com.example.yahoofinancesample.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yahoofinancesample.service.YahooFinanceAPIService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject
import com.example.yahoofinancesample.service.responsemodels.Result as MarketData

@HiltViewModel
class MarketDataListViewModel
@Inject constructor(
    private val apiService: YahooFinanceAPIService
) : ViewModel() {

    private val marketData: MutableLiveData<List<MarketData>> by lazy {
        MutableLiveData<List<MarketData>>().also {
            loadMarketData()
        }
    }

    fun getMarketData(): LiveData<List<MarketData>> {
        return marketData
    }

    private fun loadMarketData() {
        viewModelScope.launch(Dispatchers.IO) {
            while (coroutineContext.isActive) {
                val result = apiService.getMarketSummary()
                if (result.isSuccessful) {
                    marketData.postValue(arrayListOf())
                    marketData.postValue(result.body()?.marketSummaryAndSparkResponse?.result)
                    delay(8000)
                } else {
                    cancel("Oops! Something went wrong..")
                }
            }
        }
    }
}