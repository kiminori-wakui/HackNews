package com.example.hacknews.net

import android.os.Parcelable
import androidx.annotation.Keep

import kotlinx.parcelize.Parcelize


@Parcelize
@Keep
data class StatusCodeRsp(
    val statusCode: String
) : Parcelable{

}

@Parcelize
@Keep
data class RcdCodeRsp(
    val rcd: String
) : Parcelable{

}

