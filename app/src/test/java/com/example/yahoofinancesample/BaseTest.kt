package com.example.yahoofinancesample

import org.junit.Rule

open class BaseTest {

    @get:Rule
    val coroutineScope = CoroutineScopeRule()
}