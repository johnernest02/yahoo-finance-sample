package com.example.yahoofinancesample.service.responsemodels

data class Holder(
    val latestTransDate: LatestTransDate,
    val maxAge: Int,
    val name: String,
    val positionDirect: PositionDirect,
    val positionDirectDate: PositionDirectDate,
    val positionIndirect: PositionIndirect,
    val positionIndirectDate: PositionIndirectDate,
    val relation: String,
    val transactionDescription: String,
    val url: String
)