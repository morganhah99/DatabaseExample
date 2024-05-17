package com.logical.mydictionary.data.api

import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class FetchApiImpl @Inject constructor(private val fetchApi: FetchApi) {
    suspend fun getAcronym(shortForm:String)=
        fetchApi.getAcronym(shortForm)

}