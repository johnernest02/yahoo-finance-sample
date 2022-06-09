package com.example.yahoofinancesample.service.responsemodels

data class NetSharePurchaseActivity(
    val buyInfoCount: BuyInfoCount,
    val buyInfoShares: BuyInfoShares,
    val buyPercentInsiderShares: BuyPercentInsiderShares,
    val maxAge: String,
    val netInfoCount: NetInfoCount,
    val netInfoShares: NetInfoShares,
    val netPercentInsiderShares: NetPercentInsiderShares,
    val period: String,
    val sellInfoCount: SellInfoCount,
    val totalInsiderShares: TotalInsiderShares
)