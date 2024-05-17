package com.logical.mydictionary.module

import android.content.Context
import androidx.room.Room
import com.logical.mydictionary.data.database.WordsDatabase
import com.logical.mydictionary.util.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, WordsDatabase::class.java, DATABASE_NAME).build()

    @Provides
    @Singleton
    fun provideDao(database: WordsDatabase)=database.wordsDao()


}