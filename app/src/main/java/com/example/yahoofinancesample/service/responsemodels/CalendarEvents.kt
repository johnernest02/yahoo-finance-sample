package com.example.yahoofinancesample.service.responsemodels

data class CalendarEvents(
    val dividendDate: DividendDate,
    val earnings: Earnings,
    val exDividendDate: ExDividendDate,
    val maxAge: String
)