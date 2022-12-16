package com.example.hacknews.repo

import androidx.lifecycle.LiveData
import com.example.hacknews.net.LoginxReqBody
import com.example.hacknews.net.LoginxRsp

interface ILoginxResource {

    suspend fun requestLogin(body: LoginxReqBody)

    val loginRsp: LiveData<LoginxRsp>


}