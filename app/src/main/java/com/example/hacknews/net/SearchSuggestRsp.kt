package com.example.hacknews.net

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Parcelize
@Keep
data class SearchSuggestRsp(
    val cateId: List<SuggestBean>
) : Parcelable {
}


@Parcelize
@Keep
data class SuggestBean(
    val id: String,
    val label: String,
    val value: String
) : Parcelable {
}