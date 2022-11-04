package com.gk.emon.currency.local_db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class  MapConverter {
    companion object{

        @JvmStatic
        @TypeConverter
        fun fromString(value: String): Map<String, String> {
            val mapType = object : TypeToken<Map<String, String>>() {}.type
            return Gson().fromJson(value, mapType)
        }

        @TypeConverter
        @JvmStatic
        fun fromStringMap(map: Map<String, String>): String {
            val gson = Gson()
            return gson.toJson(map)
        }

    }
}