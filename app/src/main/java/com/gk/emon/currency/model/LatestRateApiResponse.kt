package com.gk.emon.currency.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LatestRateApiResponse(
    @PrimaryKey
    val id: Int,
    val disclaimer: String,
    val license: String,
    val base: String,
    val date: String?,
    val rates: Map<String, String>,
)