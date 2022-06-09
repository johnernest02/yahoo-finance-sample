package com.example.yahoofinancesample.service.responsemodels

data class QuoteType(
    val exchange: String,
    val exchangeTimezoneName: String,
    val exchangeTimezoneShortName: String,
    val gmtOffSetMilliseconds: String,
    val isEsgPopulated: Boolean,
    val longName: String,
    val market: String,
    val messageBoardId: String,
    val quoteType: String,
    val shortName: String,
    val symbol: String
)