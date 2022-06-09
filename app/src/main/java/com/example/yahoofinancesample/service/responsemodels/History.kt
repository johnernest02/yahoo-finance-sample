package com.example.yahoofinancesample.service.responsemodels

data class History(
    val action: String,
    val epochGradeDate: Int,
    val firm: String,
    val fromGrade: String,
    val toGrade: String
)