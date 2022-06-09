package com.example.yahoofinancesample.service.responsemodels

data class Trend(
    val buy: String,
    val hold: String,
    val period: String,
    val sell: String,
    val strongBuy: String,
    val strongSell: Int
)