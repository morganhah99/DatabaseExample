package com.logical.mydictionary.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.logical.mydictionary.model.ResultModel
import com.logical.mydictionary.util.Constants.TABLE_NAME
import org.jetbrains.annotations.NotNull


@Entity(tableName = TABLE_NAME)
data class WordsEntity(val resultModel: ResultModel) {
   @PrimaryKey(autoGenerate = false)
   @NotNull
   var  key: String = resultModel[0].sf.also { key = it }

}