package com.ivan.ceaicovschi.tarotdesignpattern

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DataConverter {
    @TypeConverter
    fun fromPlayer(value: PlayerResult?): String {
        val gson = Gson()
        val type = object : TypeToken<PlayerResult?>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toPlayer(value: String): PlayerResult? {
        val gson = Gson()
        val type = object : TypeToken<PlayerResult?>() {}.type
        return gson.fromJson(value, type)
    }
}