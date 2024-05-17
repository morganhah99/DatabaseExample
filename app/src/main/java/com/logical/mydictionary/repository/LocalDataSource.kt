package com.logical.mydictionary.repository

import com.logical.mydictionary.data.database.WordDao
import com.logical.mydictionary.data.database.entities.WordsEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val wordDao: WordDao) {

    fun readWords():Flow<List<WordsEntity>>
     =   wordDao.readWords()

    fun insertWords(wordsEntity: WordsEntity){
        wordDao.insertWords(wordsEntity)
    }


}