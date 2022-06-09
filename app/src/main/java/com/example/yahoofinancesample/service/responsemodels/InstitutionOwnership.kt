package com.example.yahoofinancesample.service.responsemodels

data class InstitutionOwnership(
    val maxAge: Int,
    val ownershipList: List<OwnershipX>
)