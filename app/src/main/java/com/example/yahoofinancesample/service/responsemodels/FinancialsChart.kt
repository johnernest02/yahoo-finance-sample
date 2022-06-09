package com.example.yahoofinancesample.service.responsemodels

data class FinancialsChart(
    val quarterly: List<QuarterlyX>,
    val yearly: List<Yearly>
)