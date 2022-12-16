package com.example.hacknews.net

import androidx.annotation.Keep


@Keep
data class BaseResponse(
    val code: Int,
    val data: Any?,
    val message: String?
) {
    companion object {
        const val SERVER_CODE_FAILED = 0
        const val SERVER_CODE_SUCCESS = 200
        const val SERVER_CODE_SUCCESS1 = 200
    }
}


// @Keep
// data class BaseResponse(
//     val code: Int?,
//     val `data`: Data?,
//     val message: String?
// ) {
//     @Keep
//     data class Data(
//         @SerializedName("is_register")
//         val isRegister: Int?
//     )
//     companion object {
//         const val SERVER_CODE_FAILED = 0
//         const val SERVER_CODE_SUCCESS = 1001
//     }
// }