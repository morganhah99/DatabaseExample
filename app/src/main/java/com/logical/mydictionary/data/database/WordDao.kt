package com.logical.mydictionary.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.logical.mydictionary.data.database.entities.WordsEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface WordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWords(wordsEntity: WordsEntity)

    @Query("SELECT * FROM words_table ORDER BY `key`")
    fun readWords(): Flow<List<WordsEntity>>

}