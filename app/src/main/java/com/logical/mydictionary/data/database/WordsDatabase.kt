package com.logical.mydictionary.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.logical.mydictionary.data.database.entities.WordsEntity


@Database(
    entities = [WordsEntity::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(WordsTypeConverter::class)
abstract class WordsDatabase : RoomDatabase() {
    abstract fun wordsDao(): WordDao
}