package com.example.yahoofinancesample.service.responsemodels

data class MajorHoldersBreakdown(
    val insidersPercentHeld: InsidersPercentHeld,
    val institutionsCount: InstitutionsCount,
    val institutionsFloatPercentHeld: InstitutionsFloatPercentHeld,
    val institutionsPercentHeld: InstitutionsPercentHeld,
    val maxAge: String
)