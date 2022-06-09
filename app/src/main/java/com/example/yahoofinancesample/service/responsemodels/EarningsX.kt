package com.example.yahoofinancesample.service.responsemodels

data class EarningsX(
    val earningsChart: EarningsChart,
    val financialCurrency: String,
    val financialsChart: FinancialsChart,
    val maxAge: String
)