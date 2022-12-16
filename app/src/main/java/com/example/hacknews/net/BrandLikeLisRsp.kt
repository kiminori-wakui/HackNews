package com.example.hacknews.net

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Parcelize
@Keep
data class BrandLikeLisRsp(
    val `data`: List<BrandLikeData>,
//    val error: List<Any>,
    val statusCode: String
) : Parcelable {}

@Parcelize
@Keep
data class BrandLikeData(
    val favoriteRemarks1: String,
    val favoriteRemarks10: String,
    val favoriteRemarks2: String,
    val favoriteRemarks3: String,
    val favoriteRemarks4: String,
    val favoriteRemarks5: String,
    val favoriteRemarks6: String,
    val favoriteRemarks7: String,
    val favoriteRemarks8: String,
    val favoriteRemarks9: String,
    val hit: Int,
    val id: String,
    val k: String,
    val nm: String,
    val ord: Int,
    val urlName: String
) : Parcelable {}