package com.example.yahoofinancesample.service.responsemodels

data class Transaction(
    val filerName: String,
    val filerRelation: String,
    val filerUrl: String,
    val maxAge: String,
    val moneyText: String,
    val ownership: String,
    val shares: Shares,
    val startDate: StartDate,
    val transactionText: String,
//    val value: ValueX
)