package com.example.hacknews.net

object OKService {

//    @JvmStatic
//    fun getAppToken(): String?{
//        val tokenParams = mapOf("storeId" to "WOS", "osDiv" to "02", "terminalNotificationId" to "testtest")
//
//        val responseToken = OkHttpApi.getInstance().post(tokenParams, "AP/ws/issueAuthToken/v1")
//        val responseTokenString = responseToken //.body!!.string()
//
//        if(responseTokenString == null){
//            ToastUtils.showShort("ネットワークがエラー")
//            return null
//        }
//
//        LogUtils.w("resp", responseTokenString)
//
//        val token = StringUtils.parseXMLValue(responseTokenString, "authenticatedToken")
//
//        if(token.isEmpty()){
//            ToastUtils.showShort("ネットワークがエラー")
//            return null
//        }
//
//        LogUtils.w("token $token")
//        MySpUtils.put(SP_KEY_USER_TOKEN, token)
//        return token
//    }

//    @JvmStatic
//    fun login(username: String, password: String): Boolean {
//        val token = MySpUtils.getString(SP_KEY_USER_TOKEN)
//        token ?: return false
//
//        val loginParams = mapOf("storeId" to "WOS", "authenticatedToken" to token,
//            "mailAddress" to username, "password" to password)
//
//        val responseAuth = OkHttpApi.getInstance().post(loginParams, "AP/ws/activateByPassword/v1")
//        val responseAuthString = responseAuth //.body!!.string()
//
//        if(responseAuthString == null){
//            ToastUtils.showShort("ネットワークがエラー")
//            return null
//        }
//
//        LogUtils.w("resp", responseAuthString)
//
//        val status  = StringUtils.parseXMLValue(responseAuthString, "statusCode")
//
//        if(status != "200"){
//            ToastUtils.showShort("ユーザー名またパスワードが違います。")
//            return false
//        }
//        return true
//    }


//    @JvmStatic
//    fun createGuestCard(): Boolean{
//        val token = MySpUtils.getString(SP_KEY_USER_TOKEN)
//        token ?: return false
//
//        val loginParams = mapOf("storeId" to "WOS", "authenticatedToken" to token)
//
//        val responseAuth = OkHttpApi.getInstance().post(loginParams, "AP/ws/issueDigitalCard/v1")
//        val responseAuthString = responseAuth //.body!!.string()
//
//        LogUtils.w("resp", responseAuthString)
//
//        val status  = StringUtils.parseXMLValue(responseAuthString, "statusCode")
//
//        if(status != "200"){
//            LogUtils.e("digital card error")
//            return false
//        }
//        return true
//    }




}




















