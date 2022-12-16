package com.example.hacknews.net

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Parcelize
@Keep
data class SuggestRsp2(
    val cookie: String,
    val device: String,
    val items: List<List<Item>>,
//    val labels: List<Any>,
    val merch: String,
    val num: String,
    val prod: List<String>,
    val redirect: String,
    val rqid: String,
    val spec: String,
    val spec_out: String
) : Parcelable {}

@Parcelize
@Keep
data class Item(
    val _id: String,
    val _merch: String,
    val _rev: Int,
//    val aliases: List<Any>,
    val avail: Boolean,
    val cats: List<String>,
    val create_date: String,
    val ct_url: String,
    val desc: String,
    val img_url: String,
    val last_update: String,
    val price: Int,
    val price_string: String,
    val review_comment_num: String,
    val review_img: String,
    val title: String
) : Parcelable {}