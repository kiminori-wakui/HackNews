package com.example.hacknews.net

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Parcelize
@Keep
data class CoordinateListRsp(
    val code: Int,
    val item: List<CoordItem>,
    val params: Params,
    val total: String
) : Parcelable

@Parcelize
@Keep
data class CoordItem(
    val accept: String,
    val accept_at: String,
    val cid: String,
    val coordinate_genre: String,
    val coordinate_height: String,
    val created_at: String,
    val image_url: String,
    val label_code: String,
    val label_id: String,
    val label_name: String,
    val products: List<Product>,
    val pv: String,
    val resized_main_images: List<ResizedMainImage>,
    val shop_code: String,
    val shop_id: String,
    val shop_name: String,
    val tags: List<String>,
    val team_post: String,
    val user_age: String,
    val user_attributes: List<UserAttribute>,
    val user_code: String,
    val user_gender: String,
    val user_height: String,
    val user_id: String,
    val user_image_url: String,
    val user_name: String,
    val user_name_kana: String,
    val user_resized_images: List<UserResizedImage>
) : Parcelable

@Parcelize
@Keep
data class Params(
    val gender: String,
    val merchant_id: String,
    val count: String
) : Parcelable

@Parcelize
@Keep
data class Product(
    val base_product_code: String,
    val category: String,
    val category_code: String,
    val cid: String,
    val color: String,
    val color_code: String,
    val image_url: String,
    val is_public: String,
    val jan_code: String,
    val label: String,
    val label_code: String,
    val name: String,
    val product_code: String,
    val product_url: String,
    val size: String,
    val size_code: String
) : Parcelable

@Parcelize
@Keep
data class ResizedMainImage(
//    val l: String,
//    val m: String,
    val org: String,
//    val s: String
) : Parcelable

@Parcelize
@Keep
data class UserAttribute(
    val user_attribute_type: UserAttributeType,
    val user_attribute_value: UserAttributeValue
) : Parcelable

@Parcelize
@Keep
data class UserResizedImage(
    val size: String,
    val url: String
) : Parcelable

@Parcelize
@Keep
data class UserAttributeType(
    val display_value: String,
    val slug: String,
    val unit: String
) : Parcelable

@Parcelize
@Keep
data class UserAttributeValue(
    val display_value: String
) : Parcelable













