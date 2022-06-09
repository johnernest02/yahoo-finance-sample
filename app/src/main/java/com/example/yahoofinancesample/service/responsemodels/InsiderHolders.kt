package com.example.yahoofinancesample.service.responsemodels

data class InsiderHolders(
    val holders: List<Holder>,
    val maxAge: String
)