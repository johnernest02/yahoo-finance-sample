package com.example.yahoofinancesample.service.responsemodels

data class Price(
    val averageDailyVolume10Day: AverageDailyVolume10Day,
    val averageDailyVolume3Month: AverageDailyVolume3Month,
    val circulatingSupply: CirculatingSupply,
    val currency: String,
    val currencySymbol: String,
    val exchange: String,
    val exchangeDataDelayedBy: Int,
    val exchangeName: String,
    val fromCurrency: Any,
    val lastMarket: Any,
    val longName: String,
    val marketCap: MarketCap,
    val marketState: String,
    val maxAge: Int,
    val openInterest: OpenInterest,
    val postMarketChange: PostMarketChange,
    val postMarketPrice: PostMarketPrice,
    val preMarketChange: PreMarketChange,
    val preMarketChangePercent: PreMarketChangePercent,
    val preMarketPrice: PreMarketPrice,
    val preMarketSource: String,
    val preMarketTime: Int,
    val priceHint: PriceHint,
    val quoteSourceName: String,
    val quoteType: String,
    val regularMarketChange: RegularMarketChange,
    val regularMarketChangePercent: RegularMarketChangePercent,
    val regularMarketDayHigh: RegularMarketDayHigh,
    val regularMarketDayLow: RegularMarketDayLow,
    val regularMarketOpen: RegularMarketOpen,
    val regularMarketPreviousClose: RegularMarketPreviousClose,
    val regularMarketPrice: RegularMarketPrice,
    val regularMarketSource: String,
    val regularMarketTime: Int,
    val regularMarketVolume: RegularMarketVolume,
    val shortName: String,
    val strikePrice: StrikePrice,
    val symbol: String,
    val toCurrency: Any,
    val underlyingSymbol: Any,
    val volume24Hr: Volume24Hr,
    val volumeAllCurrencies: VolumeAllCurrencies
)