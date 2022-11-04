package com.gk.emon.currency.local_db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gk.emon.currency.model.CurrenciesApiResponse
import com.gk.emon.currency.model.LatestRateApiResponse


@Database(
    entities = [CurrenciesApiResponse::class, LatestRateApiResponse::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(MapConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getCurrencyDao(): CurrencyDao
    abstract fun getLatestRateDao(): LatestRateDao

    companion object {

        private var INSTANCE: AppDatabase? = null

        private val lock = Any()

        fun getInstance(context: Context): AppDatabase {
            synchronized(lock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java, "currency.db"
                    ).allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
                return INSTANCE!!
            }
        }
    }
}
