package com.example.yahoofinancesample.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yahoofinancesample.service.Resource
import com.example.yahoofinancesample.service.YahooFinanceAPIService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject
import com.example.yahoofinancesample.service.responsemodels.Result as MarketData

@HiltViewModel
class MarketDataListViewModel
@Inject constructor(
    private val apiService: YahooFinanceAPIService,
    private val coroutineDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val marketData: MutableLiveData<Resource<List<MarketData>>> by lazy {
        MutableLiveData<Resource<List<MarketData>>>().also {
            loadMarketData()
        }
    }

    fun getMarketData(): LiveData<Resource<List<MarketData>>> {
        return marketData
    }

    private fun loadMarketData() {
        viewModelScope.launch(coroutineDispatcher) {
            while (coroutineContext.isActive) {
                marketData.postValue(Resource.Loading())
                val result = apiService.getMarketSummary()
                if (result.isSuccessful) {
                    var retrievedList = result.body()?.marketSummaryAndSparkResponse?.result
                    if (retrievedList != null) {
                        marketData.postValue(Resource.Success(arrayListOf()))
                        marketData.postValue(Resource.Success(retrievedList))
                    } else {
                        marketData.postValue(Resource.Failure(Throwable("No market summary retrieved")))
                    }
                    delay(8000)
                } else {
                    marketData.postValue(Resource.Failure(Throwable(result.errorBody().toString())))
                    cancel("Oops! Something went wrong..")
                }
            }
        }
    }
}