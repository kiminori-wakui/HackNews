package com.example.hacknews.net

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Parcelize
@Keep
data class GoodsStoreStockRsp(
    val statusCode: String,
    val `data`: StoreStockData,
) : Parcelable

@Parcelize
@Keep
data class StoreStockData(
    val biCd: String,
    val coId: String,
    val coNm: String,
    val szId: String,
    val szNm: String,
    val list: List<StoreStock>
) : Parcelable

@Parcelize
@Keep
data class StoreStock(
    val exBrId: String,
    val exBrNm: String,
    val pfId: String,
    val pfNm: String,
    val rsId: String,
    val rsCd: String,
    val rsNm: String,
    val rssNum: String,
    val distance: String
) : Parcelable
