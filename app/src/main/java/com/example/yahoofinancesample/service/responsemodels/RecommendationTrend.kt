package com.example.yahoofinancesample.service.responsemodels

data class RecommendationTrend(
    val maxAge: String,
    val trend: List<Trend>
)