package com.example.koin.room

import androidx.room.TypeConverter
import com.example.koin.network_data.TrailerList
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TrailerConverter {

    @TypeConverter
    fun trailers2String(list: TrailerList?): String?{
        if (list == null){
            return null
        }
        return Gson().toJson(list)
    }

    @TypeConverter
    fun string2List(json: String?): TrailerList?{
        if (json == null){
            return null
        }
        val type = object : TypeToken<TrailerList>(){}.type
        return Gson().fromJson<TrailerList>(json, type)
    }
}