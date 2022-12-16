package com.example.hacknews.net

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Parcelize
@Keep
data class MailMagazineLikeListRsp(
    val `data`: List<MailMagazineLikeData>,
//    val error: List<Any>,
    val statusCode: String
) : Parcelable {}

@Parcelize
@Keep
data class MailMagazineLikeData(
    val mmId: String,
    val mmDiv: String,
    val mmNm: String,
    val exBrId: String,
    val exBrNm: String,
    val rsId: String,
    val rsNm: String
) : Parcelable {}