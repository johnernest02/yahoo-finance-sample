package com.example.yahoofinancesample.service

sealed class Resource<out T> {
    class Loading<Nothing> : Resource<Nothing>()
    data class Success<out T>(val data: T) : Resource<T>()
    data class Failure<out T>(val throwable: Throwable) : Resource<T>()
}