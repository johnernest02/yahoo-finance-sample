package com.example.yahoofinancesample

import android.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.yahoofinancesample.service.Resource
import com.example.yahoofinancesample.service.YahooFinanceAPIService
import com.example.yahoofinancesample.ui.list.MarketDataListViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import java.util.*
import com.example.yahoofinancesample.service.responsemodels.Result as MarketData

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MarketDataListViewModelTest: BaseTest() {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var yahooFinanceAPIService: YahooFinanceAPIService

    private lateinit var marketDataListViewModel: MarketDataListViewModel

    @Mock
    private lateinit var marketDataObserver: Observer<Resource<List<MarketData>>>

    @Before
    fun setUp() {
        marketDataListViewModel = MarketDataListViewModel(yahooFinanceAPIService)
    }

    @Test
    fun `Given loadMarketData is called, When no error occurs, Then getMarketSummary should be invoked successfully`() = runBlocking {
        coroutineScope.runBlockingTest {
            doReturn(emptyList<MarketData>())
                .`when`(yahooFinanceAPIService)
                .getMarketSummary()
            marketDataListViewModel.getMarketData().observeForever(marketDataObserver)
            verify(yahooFinanceAPIService).getMarketSummary()
            verify(marketDataObserver).onChanged(Resource.Success(emptyList()))
            marketDataListViewModel.getMarketData().removeObserver(marketDataObserver)
        }
    }
}