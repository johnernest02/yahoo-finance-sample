package com.example.yahoofinancesample.service

import com.example.yahoofinancesample.service.responsemodels.MarketSummary
import com.example.yahoofinancesample.service.responsemodels.StockSummary
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface YahooFinanceAPIService {

    @GET("/market/v2/get-summary?region=US")
    suspend fun getMarketSummary() : Response<MarketSummary>

    @GET("/stock/v2/get-summary?")
    suspend fun getStockSummary(@Query("symbol")  symbol: String, @Query("region")  region: String) : Response<StockSummary>
}