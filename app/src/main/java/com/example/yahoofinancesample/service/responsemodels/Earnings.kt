package com.example.yahoofinancesample.service.responsemodels

data class Earnings(
    val earningsAverage: EarningsAverage,
    val earningsDate: List<EarningsDate>,
    val earningsHigh: EarningsHigh,
    val earningsLow: EarningsLow,
    val revenueAverage: RevenueAverage,
    val revenueHigh: RevenueHigh,
    val revenueLow: RevenueLow
)