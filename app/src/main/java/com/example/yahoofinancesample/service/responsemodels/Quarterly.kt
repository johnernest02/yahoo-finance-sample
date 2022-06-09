package com.example.yahoofinancesample.service.responsemodels

data class Quarterly(
    val `actual`: Actual,
    val date: String,
    val estimate: Estimate
)