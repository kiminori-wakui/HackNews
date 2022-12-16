package com.example.hacknews.net

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize


@Parcelize
@Keep
data class CartInfoRsp(
    val `data`: Data,
    val statusCode: String
) : Parcelable {}


@Parcelize
@Keep
data class Data(
    val totalAmount: Int,
    val totalPrice: String
) : Parcelable{}




@Parcelize
@Keep
data class CartAddRsp(
    val `data`: Data,
    val statusCode: String
) : Parcelable{}