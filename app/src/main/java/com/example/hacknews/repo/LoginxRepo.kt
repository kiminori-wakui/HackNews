package com.example.hacknews.repo

import android.util.Log.w
import androidx.lifecycle.LiveData
import androidx.startup.StartupLogger.w
import jp.co.world.service.net.*
import com.example.hacknews.net.*
import com.google.android.gms.vision.L.w
import com.google.android.gms.vision.clearcut.LogUtils
import jp.co.world.common.model.SingleLiveData
import jp.co.world.common.network.support.serverData

class LoginxRepo(private val service: LoginxService): ILoginxResource {

    private val _loginRsp = SingleLiveData<LoginxRsp>()

    override val loginRsp: LiveData<LoginxRsp> = _loginRsp


    override suspend fun requestLogin(body: LoginxReqBody) {
        service.login(body)
            .serverData()
            .onSuccess {
                onBizError{ code, message ->
                }
                onBizOK<LoginxRsp> { code, data, message ->
                    _loginRsp.value = data


                    return@onBizOK
                }

            }
            .onFailure {
            }
    }







}















