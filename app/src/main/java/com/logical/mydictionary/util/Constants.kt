package com.logical.mydictionary.util

import androidx.lifecycle.LiveData

object Constants {
    const val BASE_URL="http://www.nactem.ac.uk/software/acromine/"
    const val SEARCH = "search"
    const val SAVED = "saved"
    const val TABLE_NAME = "words_table"
    const val DATABASE_NAME = "words_database"
    const val ARGS_KEY = "information"
    const val ARGS_KEY_DESCRIPTION = "description"


    fun <T> LiveData<T>.observeOnce(onChangeHandler: (T) -> Unit) {
        val observer = OneTimeObserver(handler = onChangeHandler)
        observe(observer, observer)
    }


}