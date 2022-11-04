package com.gk.emon.currency

import com.gk.emon.currency.network.CurrencyApiService
import com.gk.emon.currency.network.ResultCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
var retrofit: Retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(ResultCallAdapterFactory())
    .baseUrl("https://openexchangerates.org/api/")
    .client(
        OkHttpClient.Builder().apply {
            this.addInterceptor(HttpLoggingInterceptor().apply {
                this.level = HttpLoggingInterceptor.Level.BODY
            })
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
        }.build()
    )
    .build()

var service: CurrencyApiService = retrofit.create(CurrencyApiService::class.java)