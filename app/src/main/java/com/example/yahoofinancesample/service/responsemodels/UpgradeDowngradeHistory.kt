package com.example.yahoofinancesample.service.responsemodels

data class UpgradeDowngradeHistory(
    val history: List<History>,
    val maxAge: Int
)