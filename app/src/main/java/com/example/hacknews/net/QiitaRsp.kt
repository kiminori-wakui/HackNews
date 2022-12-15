package com.example.hacknews.net

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class QiitaRsp(
    val response: ArrayList<Qiita>?,
): Parcelable

@Keep
@Parcelize
data class Qiita(
    val rendered_body: String?,
    val body: String?,
    val coediting: Boolean?,
    val comments_count: Int?,
    val created_at: String?,
    val group: ArrayList<Group>?,
    val id: String?,
    val likes_count: Int?,
    val private: Boolean?,
    val reactions_count: Int?,
    val stocks_count: Int?,
    val tags: ArrayList<Tag>?,
    val title: String?,
    val updated_at: String?,
    val url: String?,
    val user: ArrayList<User>?,
    val page_views_count: Int?,
    val team_membership: ArrayList<TeamMembership>?,
): Parcelable

@Keep
@Parcelize
data class Group(
    val created_at: String?,
    val description: String?,
    val name: String?,
    val private: Int?,
    val updated_at: String?,
    val url_name: String?,
): Parcelable

@Keep
@Parcelize
data class Tag(
    val name: String?,
    val versions: ArrayList<String>?,
): Parcelable

@Keep
@Parcelize
data class User(
    val description: String?,
    val facebook_id: String?,
    val followees_count: Int?,
    val followers_count: Int?,
    val github_login_name: String?,
    val id: String?,
    val items_count: Int?,
    val linkedin_id: String?,
    val location: String?,
    val name: String?,
    val organization: String?,
    val permanent_id: Int?,
    val profile_image_url: String?,
    val team_only: Boolean?,
    val twitter_screen_name: String?,
    val website_url: String?,
): Parcelable

@Keep
@Parcelize
data class TeamMembership(
    val name: String?,
): Parcelable
