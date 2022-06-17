package com.example.yahoofinancesample

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.lifecycle.map
import com.example.yahoofinancesample.service.Resource
import com.example.yahoofinancesample.service.YahooFinanceAPIService
import com.example.yahoofinancesample.service.responsemodels.*
import com.example.yahoofinancesample.ui.list.MarketDataListViewModel
import com.example.yahoofinancesample.utils.observeForTesting
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.yield
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.AdditionalAnswers
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.capture
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.stubbing.Answer
import retrofit2.Invocation
import retrofit2.Response
import kotlin.reflect.typeOf
import com.example.yahoofinancesample.service.responsemodels.Result as MarketData

@ExperimentalCoroutinesApi
class MarketDataListViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    // Sets the main coroutines dispatcher to a TestCoroutineScope for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var yahooFinanceAPIService: YahooFinanceAPIService

    private lateinit var marketDataListViewModel: MarketDataListViewModel

    private val marketDataObserver: Observer<Resource<List<MarketData>>> = mock()

    private val sampleMarketSummary = MarketSummary(
        marketSummaryAndSparkResponse = MarketSummaryAndSparkResponse(
            error = null,
            result = listOf(
                MarketData(
                    exchangeTimezoneName = "America/New_York",
                    fullExchangeName = "CME",
                    symbol = "ES=F",
                    gmtOffSetMilliseconds = -14400000,
                    exchangeDataDelayedBy = 10,
                    firstTradeDateMilliseconds = 969249600000,
                    language = "en-US",
                    exchangeTimezoneShortName = "EDT",
                    quoteType = "FUTURE",
                    customPriceAlertConfidence = "NONE",
                    marketState = "REGULAR",
                    market = "us24_market",
                    priceHint = 2,
                    tradeable = false,
                    sourceInterval = 10,
                    exchange = "CME",
                    shortName = "S&P Futures",
                    region = "US",
                    triggerable = false,
                    regularMarketPreviousClose = RegularMarketPreviousCloseX(
                        raw = "4016.25",
                        fmt = "4,016.25",
                    ),
                    regularMarketTime = RegularMarketTime(
                        raw = "1654857400",
                        fmt = "6:36AM EDT",
                    ),
                    spark = Spark(
                        symbol = "ES=F",
                        previousClose = 4016.25,
                        chartPreviousClose = 4016.25,
                        dataGranularity = "300",
                        end = "1654919940",
                        start = "1654833600",
                        timestamp = listOf(1654833600, 1654833900),
                        close = listOf(4024.75, 4023.5)
                    )
                )
            )
        )
    )

    @Before
    fun setUp() {
        yahooFinanceAPIService = mock()
        marketDataListViewModel =
            MarketDataListViewModel(yahooFinanceAPIService, mainCoroutineRule.testDispatcher)
    }

    @Test
    fun `getMarketData should return response from API Service every 8 seconds`() =
        runTest {
            doReturn(Response.success(sampleMarketSummary))
                .`when`(yahooFinanceAPIService)
                .getMarketSummary()

            marketDataListViewModel.getMarketData().observeForTesting {
                verify(marketDataObserver, after(16000).atLeast(2)).onChanged(Resource.Success(sampleMarketSummary.marketSummaryAndSparkResponse.result))
            }
        }
}