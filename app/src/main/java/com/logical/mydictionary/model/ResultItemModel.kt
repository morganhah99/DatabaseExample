package com.logical.mydictionary.model


import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
class ResultItemModel(

    @SerializedName("lfs")
    val lfs: @RawValue List<LfModel>,

    @SerializedName("sf")
    val sf: String

) : Parcelable