package com.example.yahoofinancesample.ui.detail

import androidx.lifecycle.*
import com.example.yahoofinancesample.service.Resource
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

    private val stockSummary: MutableLiveData<Resource<StockSummary>> by lazy {
        MutableLiveData<Resource<StockSummary>>().also {
            loadStockSummary()
        }
    }

    fun getStockSummary(): LiveData<Resource<StockSummary>> {
        return stockSummary
    }

    private fun loadStockSummary() {
        viewModelScope.launch(Dispatchers.IO) {
            stockSummary.postValue(Resource.Loading())
            val result = symbol.value?.let { apiService.getStockSummary(it, "US") }
            if (result?.isSuccessful == true) {
                var stockSummaryData = result.body()
                if(stockSummaryData != null) {
                    stockSummary.postValue(Resource.Success(stockSummaryData))
                } else {
                    stockSummary.postValue(Resource.Failure(Throwable("No stock summary received")))
                }
            } else {
                stockSummary.postValue(Resource.Failure(Throwable(result?.errorBody().toString())))
                cancel("Oops! Something went wrong..")
            }
        }
    }
}