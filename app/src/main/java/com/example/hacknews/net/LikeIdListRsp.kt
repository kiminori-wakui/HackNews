package com.example.hacknews.net

data class LikeIdListRsp(
    val `data`: List<String>,
    val error: List<Any>,
    val statusCode: String
)