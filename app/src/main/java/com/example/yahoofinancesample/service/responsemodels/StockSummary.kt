package com.example.yahoofinancesample.service.responsemodels

data class StockSummary(
    val calendarEvents: CalendarEvents,
    val defaultKeyStatistics: DefaultKeyStatistics,
    val details: Details,
    val earnings: EarningsX,
    val esgScores: EsgScores,
    val financialData: FinancialData,
    val financialsTemplate: FinancialsTemplate,
    val fundOwnership: FundOwnership,
    val insiderHolders: InsiderHolders,
    val insiderTransactions: InsiderTransactions,
    val institutionOwnership: InstitutionOwnership,
    val majorDirectHolders: MajorDirectHolders,
    val majorHoldersBreakdown: MajorHoldersBreakdown,
    val netSharePurchaseActivity: NetSharePurchaseActivity,
    val pageViews: PageViews,
    val price: Price,
    val quoteType: QuoteType,
    val recommendationTrend: RecommendationTrend,
    val summaryDetail: SummaryDetail,
    val summaryProfile: SummaryProfile,
    val symbol: String,
    val upgradeDowngradeHistory: UpgradeDowngradeHistory
)