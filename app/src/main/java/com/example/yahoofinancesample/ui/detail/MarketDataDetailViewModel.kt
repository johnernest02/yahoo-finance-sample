package com.example.yahoofinancesample.ui.detail

import androidx.lifecycle.*
import com.example.yahoofinancesample.service.YahooFinanceAPIService
import com.example.yahoofinancesample.service.responsemodels.StockSummary
import com.example.yahoofinancesample.ui.detail.MarketDataDetailFragment.Companion.ARG_SYMBOL
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class MarketDataDetailViewModel
@Inject constructor(
    private val apiService: YahooFinanceAPIService,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val symbol = savedStateHandle.getLiveData<String>(ARG_SYMBOL)

    private val stockSummary: MutableLiveData<StockSummary> by lazy {
        MutableLiveData<StockSummary>().also {
            loadStockSummary()
        }
    }

    fun getStockSummary(): LiveData<StockSummary> {
        return stockSummary
    }

    private fun loadStockSummary() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = symbol.value?.let { apiService.getStockSummary(it, "US") }
            if (result?.isSuccessful == true) {
                stockSummary.postValue(result.body())
                delay(8000)
            } else {
                cancel("Oops! Something went wrong..")
            }
        }
    }
}