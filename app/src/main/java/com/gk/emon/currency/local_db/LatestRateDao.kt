package com.gk.emon.currency.local_db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gk.emon.currency.model.CurrenciesApiResponse
import com.gk.emon.currency.model.LatestRateApiResponse

@Dao
interface LatestRateDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllLatestRates(latestRateApiResponse : LatestRateApiResponse)
}