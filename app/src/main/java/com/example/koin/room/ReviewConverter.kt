package com.example.koin.room

import androidx.room.TypeConverter
import com.example.koin.network_data.ReviewList
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ReviewConverter {

    @TypeConverter
    fun list2String(list: ReviewList?):String?{
        if (list == null){
            return null
        }
        return Gson().toJson(list)
    }

    @TypeConverter
    fun string2List(json:String?): ReviewList?{
        if (json == null){
            return null
        }
        val type = object : TypeToken<ReviewList>(){}.type
        return Gson().fromJson<ReviewList>(json, type)
    }
}