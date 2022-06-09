package com.example.yahoofinancesample.service.responsemodels

data class InsiderTransactions(
    val maxAge: String,
    val transactions: List<Transaction>
)