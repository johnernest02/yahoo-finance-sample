package com.example.yahoofinancesample.di

import com.example.yahoofinancesample.service.YahooFinanceAPIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
internal object ServicesModule {

    @Provides
    fun providesRetrofit(): Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder().apply {
            addInterceptor(
                Interceptor { chain ->
                    return@Interceptor chain.proceed(chain.request().newBuilder()
                        .addHeader("X-RapidAPI-Key", "dfe8ef94a7mshc129e58deccf5c4p192952jsn6de6cc686c3d")
                        .addHeader("X-RapidAPI-Host", "yh-finance.p.rapidapi.com")
                        .build())
                }
            )
            addInterceptor(loggingInterceptor)
        }.build()
        return Retrofit.Builder().baseUrl("https://yh-finance.p.rapidapi.com")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideYahooFinanceAPIService(retrofit: Retrofit): YahooFinanceAPIService {
        return retrofit.create(YahooFinanceAPIService::class.java)
    }

    @Provides
    fun provideCoroutineDispatcher(): CoroutineDispatcher {
        return Dispatchers.Default
    }
}