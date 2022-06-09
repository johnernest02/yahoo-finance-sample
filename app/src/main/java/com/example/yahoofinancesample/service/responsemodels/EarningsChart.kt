package com.example.yahoofinancesample.service.responsemodels

data class EarningsChart(
    val currentQuarterEstimate: CurrentQuarterEstimate,
    val currentQuarterEstimateDate: String,
    val currentQuarterEstimateYear: Int,
    //val earningsDate: List<EarningsDateX>,
    val quarterly: List<Quarterly>
)