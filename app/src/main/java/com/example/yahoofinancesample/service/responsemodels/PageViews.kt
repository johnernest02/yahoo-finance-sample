package com.example.yahoofinancesample.service.responsemodels

data class PageViews(
    val longTermTrend: String,
    val maxAge: String,
    val midTermTrend: String,
    val shortTermTrend: String
)