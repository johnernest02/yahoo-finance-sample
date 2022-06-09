package com.example.yahoofinancesample.service.responsemodels

data class Spark(
    val chartPreviousClose: Double,
    val close: List<Double>,
    val dataGranularity: String,
    val end: String,
    val previousClose: Double,
    val start: String,
    val symbol: String,
    val timestamp: List<Int>
)