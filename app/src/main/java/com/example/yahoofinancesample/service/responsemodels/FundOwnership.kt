package com.example.yahoofinancesample.service.responsemodels

data class FundOwnership(
    val maxAge: Int,
    val ownershipList: List<Ownership>
)