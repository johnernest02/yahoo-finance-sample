package com.example.yahoofinancesample.service.responsemodels

data class SummaryProfile(
    val address1: String,
    val address2: String,
    val city: String,
    val companyOfficers: List<Any>,
    val country: String,
    val fullTimeEmployees: Int,
    val industry: String,
    val longBusinessSummary: String,
    val maxAge: Int,
    val phone: String,
    val sector: String,
    val website: String,
    val zip: String
)