package com.logical.mydictionary.repository

import com.logical.mydictionary.data.api.FetchApiImpl
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val fetchApiImpl: FetchApiImpl) {
    suspend fun getAcronym(shortForm:String) =
        fetchApiImpl.getAcronym(shortForm)

}