package com.gk.emon.currency.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CurrenciesApiResponse(
    @PrimaryKey
    val id: Int,
    val currencies: Map<String, String>,
)