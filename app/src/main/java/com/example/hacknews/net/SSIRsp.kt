package com.example.hacknews.net

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Parcelize
@Keep
data class SSIRsp(
    var statusCode: String,
    var data: SSIBody?,
) : Parcelable {

}



@Parcelize
@Keep
data class SSIBody(
    var customerId: String,
    var ssi: String
) : Parcelable {

}
