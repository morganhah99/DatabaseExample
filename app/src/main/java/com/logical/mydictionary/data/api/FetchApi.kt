package com.logical.mydictionary.data.api

import com.logical.mydictionary.model.ResultModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FetchApi {
    @GET("dictionary.py?")
    suspend fun getAcronym(
     @Query("sf") sf:String
    ):Response<ResultModel>

}