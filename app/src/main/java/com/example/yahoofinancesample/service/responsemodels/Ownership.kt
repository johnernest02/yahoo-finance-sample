package com.example.yahoofinancesample.service.responsemodels

data class Ownership(
    val maxAge: String,
    val organization: String,
    val pctHeld: PctHeld,
    val position: Position,
    val reportDate: ReportDate,
    val value: Value
)