package com.gk.emon.currency.network

import com.gk.emon.currency.model.CurrenciesApiResponse
import com.gk.emon.currency.model.LatestRateApiResponse
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*


interface CurrencyApiService {
    @GET("latest.json")
    suspend fun getLatestExchangeRates(@Query("app_id") appId:String):
            Response<LatestRateApiResponse>

    @GET("currencies.json")
    suspend fun getCurrencies(@Query("app_id") appId:String): Response<JsonObject>

}