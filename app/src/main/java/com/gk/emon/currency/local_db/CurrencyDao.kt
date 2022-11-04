package com.gk.emon.currency.local_db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gk.emon.currency.model.CurrenciesApiResponse

@Dao
interface CurrencyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCurrency(currenciesApiResponse: CurrenciesApiResponse)

    @Query("SELECT * FROM CurrenciesApiResponse")
    fun getAll(): CurrenciesApiResponse
}