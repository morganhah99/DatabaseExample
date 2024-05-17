package com.logical.mydictionary.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.logical.mydictionary.model.ResultModel

class WordsTypeConverter {

    private val gson = Gson()

    @TypeConverter
    fun resultModelToString(resultModel: ResultModel): String =
        gson.toJson(resultModel)

    @TypeConverter
    fun stringToPeopleModel(data: String): ResultModel {
        val listType = object : TypeToken<ResultModel>() {}.type
        return gson.fromJson(data, listType)
    }



}