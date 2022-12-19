package jp.co.world.common.utils

import com.example.hacknews.BuildConfig
import jp.co.world.common.network.config.SP_KEY_BASE_HOST


fun getBaseHost(): String {
    return if (BuildConfig.DEBUG) {
        HOST_DEV
    } else {
        HOST_PRODUCT
    }
}

fun getWordBaseHost(): String {
    return if (BuildConfig.DEBUG) {
        WORD_DEV
    } else {
        WORD_DEV
    }
}



fun getVoiceBaseHost(): String {
    return if (BuildConfig.DEBUG) {
        VOICE_DEV
    } else {
        VOICE_DEV
    }
}


fun getSilverBaseHost(): String {
    return if (BuildConfig.DEBUG) {
        SILVER_DEV
    } else {
        SILVER_DEV
    }
}


fun getStaffStartBaseHost(): String {
    return if (BuildConfig.DEBUG) {
        HOST_SNAP_TEST
    } else {
        HOST_SNAP_PROD
    }
}


// 検証環境
const val HOST_DEV = "https://wos-chk.fashion-co-lab.jp/"
const val SILVER_DEV = "https://worldfcl-test.silveregg.net/"
const val VOICE_DEV = "https://dev.voice.zetacx.net/"
const val WORD_DEV = "https://worldv3.search.zetacx.net/"

const val HOST_PRODUCT = "https://wos-chk.fashion-co-lab.jp/"


const val VOICE_SITE_ID = "02b5d22f-3d4d-4085-b285-a2d9d51420a4"
const val VOICE_MASTER_ID = "88"
const val VOICE_SUMMARY_QUESTION_ID = "894"


const val URL_MAGZINE = "https://store.world.co.jp/member/incoming-mail-list"
const val URL_ACTK = "https://wos-chk.fashion-co-lab.jp/app/actk.html"

const val HOST_WEB = "https://wos-chk.fashion-co-lab.jp"
const val HOST_WEB_COOKIE = "wos-chk.fashion-co-lab.jp"

const val HOST_XML = "https://service-w-apl-chk.fashion-co-lab.jp/"

const val HOST_SNAP_TEST = "https://test.staff-start.com/"
const val HOST_SNAP_PROD = "https://api.staff-start.com/"
const val MERCHANT_ID = "1d7b33651d69011b7776d689ab933ccf"

const val QUESTION_SCORE = 890
const val QUESTION_FEEL = 894
const val QUESTION_TEXT = 895
const val QUESTION_NICK = 889
const val QUESTION_HEIGHT = 892
const val QUESTION_SLIM = 893

const val PUSH_APP_ID = "22beecab-4d0e-410e-8cee-cb254df04c0c"
const val PUSH_ACCESS_TOKEN = "1pD0dzNPiuV0QD7qKeuNxu5F"
const val PUSH_SENDER_ID = "495009624690"
const val PUSH_MID = "100016222"
const val PUSH_SERVER_URL = "https://mcpn5d68fgfclffnq3cf5vz6mmc0.device.marketingcloudapis.com/"




// 本番環境
//const val HOST_DEV = "https://store.world.co.jp/"
//const val SILVER_DEV = "https://worldfcl.silveregg.net/"
//const val VOICE_DEV = "https://voice.zetacx.net/"
//const val WORD_DEV = "https://worldv3.search.zetacx.net/"
//
//const val HOST_PRODUCT = "https://store.world.co.jp/"
//
//const val VOICE_SITE_ID = "e875613b-e28d-4b6e-bfe5-54bfcc2704ac"
//const val VOICE_MASTER_ID = "114"
//const val VOICE_SUMMARY_QUESTION_ID = "1421"
//
//const val URL_MAGZINE = "https://store.world.co.jp/member/incoming-mail-list"
//const val URL_ACTK = "https://store.world.co.jp/app/actk.html"
//
//const val HOST_WEB = "https://store.world.co.jp"
//const val HOST_WEB_COOKIE = "store.world.co.jp"
//
//const val HOST_XML = "https://service-w-apl.fashion-co-lab.jp/"
//
//const val HOST_SNAP_TEST = "https://api.staff-start.com/"
//const val HOST_SNAP_PROD = "https://api.staff-start.com/"
//const val MERCHANT_ID = "1d7b33651d69011b7776d689ab933ccf"
//
//const val QUESTION_SCORE = 1356
//const val QUESTION_FEEL = 1421
//const val QUESTION_TEXT = 1361
//const val QUESTION_NICK = 1355
//const val QUESTION_HEIGHT = 1358
//const val QUESTION_SLIM = 1359
//
//const val PUSH_APP_ID = "c86f5b8d-b1b2-4c52-b76e-75625b0fa31d"
//const val PUSH_ACCESS_TOKEN = "YNFnCdUUsMsJvj8n7rce5o2V"
//const val PUSH_SENDER_ID = "495009624690"
//const val PUSH_MID = "100016222"
//const val PUSH_SERVER_URL = "https://mcpn5d68fgfclffnq3cf5vz6mmc0.device.marketingcloudapis.com/"









