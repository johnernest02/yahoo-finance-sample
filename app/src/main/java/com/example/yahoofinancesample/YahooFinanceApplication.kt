package com.example.yahoofinancesample

import android.app.Application
import android.support.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class YahooFinanceApplication: MultiDexApplication() {

}